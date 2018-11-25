package com.wevat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevat.exception.InvalidResponseException;
import com.wevat.exception.QuoteNotFoundException;
import com.wevat.model.Quote;
import com.wevat.model.dto.PaymentDetails;
import com.wevat.service.QuoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static com.wevat.TestDataSetup.getPaymentDetails;
import static com.wevat.TestDataSetup.getQuote;
import static com.wevat.TestDataSetup.getQuotes;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuoteService quoteService;

    @Test
    public void shouldReturnQuoteById() throws Exception {
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();

        when(quoteService.getQuoteById("test-id")).thenReturn(quote);
        mvc.perform(get("/wevat/api/v1/quotes/test-id")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("target", is("AUD")))
                .andExpect(jsonPath("transferWiseQuoteId", is("test-transfer-wise-id")));
    }

    @Test
    public void shouldReturnAllQuotes() throws Exception {
        List<Quote> quotes = getQuotes();
        when(quoteService.getAllQuotes()).thenReturn(quotes);
        mvc.perform(get("/wevat/api/v1/quotes")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("test-id")))
                .andExpect(jsonPath("$[0].target", is("AUD")))
                .andExpect(jsonPath("$[0].transferWiseQuoteId", is("test-transfer-wise-id")));
    }

    @Test
    public void shouldCreateQuoteWithGivenDetails() throws Exception {
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        PaymentDetails paymentDetails = getPaymentDetails();
        String paymentDetailsJson = convertObjectToJsonBytes(paymentDetails);

        when(quoteService.createQuote(Mockito.any())).thenReturn(quote);
        mvc.perform(post("/wevat/api/v1/quotes")
                .contentType("application/json")
                .accept("application/json")
                .content(paymentDetailsJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("target", is("AUD")))
                .andExpect(jsonPath("transferWiseQuoteId", is("test-transfer-wise-id")));
    }

    @Test
    public void shouldUpdateQuoteWithGivenDetails() throws Exception {
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        String quoteJson = convertObjectToJsonBytes(quote);

        when(quoteService.updateQuote(Mockito.any())).thenReturn(quote);
        mvc.perform(put("/wevat/api/v1/quotes")
                .contentType("application/json")
                .accept("application/json")
                .content(quoteJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is("test-id")))
                .andExpect(jsonPath("target", is("AUD")))
                .andExpect(jsonPath("transferWiseQuoteId", is("test-transfer-wise-id")));
    }

    @Test
    public void shouldDeleteQuoteExistWithGivenId() throws Exception {
        doNothing().when(quoteService).deleteQuote("test-id");
        mvc.perform(delete("/wevat/api/v1/quotes/test-id")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowAnExceptionForGivenInvalidQuoteId() throws Exception {
        doThrow(QuoteNotFoundException.class).when(quoteService).getQuoteById("test-quote-id");
        mvc.perform(get("/wevat/api/v1/quotes/test-quote-id")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldThrowInvalidResponseExceptionWhenTransferWiseSendInValidResponse() throws Exception {
        PaymentDetails paymentDetails = getPaymentDetails();
        String paymentDetailsJson = convertObjectToJsonBytes(paymentDetails);
        doThrow(InvalidResponseException.class).when(quoteService).createQuote(Mockito.any());

        mvc.perform(post("/wevat/api/v1/quotes")
                .contentType("application/json")
                .accept("application/json")
                .content(paymentDetailsJson))
                .andExpect(status().isInternalServerError());
    }

    private String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
