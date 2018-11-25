package com.wevat.service.impl;

import com.wevat.exception.InvalidResponseException;
import com.wevat.exception.QuoteNotFoundException;
import com.wevat.model.Quote;
import com.wevat.model.User;
import com.wevat.model.dto.PaymentDetails;
import com.wevat.model.dto.TransferWiseQuote;
import com.wevat.respository.QuoteRepository;
import com.wevat.service.UserService;
import com.wevat.util.RestTemplateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.wevat.TestDataSetup.getPaymentDetails;
import static com.wevat.TestDataSetup.getQuote;
import static com.wevat.TestDataSetup.getQuotes;
import static com.wevat.TestDataSetup.getResponseEntity;
import static com.wevat.TestDataSetup.getTransferWiseQuote;
import static com.wevat.TestDataSetup.getUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceImplTest {

    private QuoteServiceImpl quoteService;

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private UserService userService;

    @Mock
    private RestTemplateUtil restTemplateUtil;

    @Before
    public void setUp() {
        quoteService = new QuoteServiceImpl(userService, quoteRepository, restTemplateUtil);
    }

    @Test
    public void shouldReturnQuoteForGivenValidId() {
        when(quoteRepository.findById("test-id")).thenReturn(getQuote());

        Quote actualQuote = quoteService.getQuoteById("test-id");

        assertNotNull(actualQuote);
        assertThat(actualQuote.getId(), is("test-id"));
        assertThat(actualQuote.getTransferWiseQuoteId(), is("test-transfer-wise-id"));
    }

    @Test(expected = QuoteNotFoundException.class)
    public void shouldThrowExceptionWhenIdIsInvalid() {
        when(quoteRepository.findById("test-id")).thenThrow(QuoteNotFoundException.class);
        quoteService.getQuoteById("test-id");
    }

    @Test
    public void shouldReturnListOfQuotes() {
        when(quoteRepository.findAll()).thenReturn(getQuotes());

        List<Quote> quotes = quoteService.getAllQuotes();

        assertNotNull(quotes);
        assertThat(quotes.get(0).getId(), is("test-id"));
    }

    @Test
    public void shouldCallTransferWiseAndCreateQuoteBasedOnResponse() {
        User user = getUser().isPresent() ? getUser().get() : new User();
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        TransferWiseQuote transferWiseQuote = getTransferWiseQuote();
        PaymentDetails paymentDetails = getPaymentDetails();

        when(userService.getUserById("test-user-id")).thenReturn(user);
        when(restTemplateUtil.post(any(), any())).thenReturn(getResponseEntity(transferWiseQuote));
        when(quoteRepository.save(any())).thenReturn(quote);

        Quote actualQuote = quoteService.createQuote(paymentDetails);

        assertNotNull(actualQuote);
        assertThat(actualQuote.getId(), is("test-id"));
    }

    @Test(expected = InvalidResponseException.class)
    public void shouldThrowAnExceptionIfTransferWiseFailedToGiveResponse() {
        User user = getUser().isPresent() ? getUser().get() : new User();

        PaymentDetails paymentDetails = getPaymentDetails();
        when(userService.getUserById("test-user-id")).thenReturn(user);
        when(restTemplateUtil.post(any(), any())).thenReturn(getResponseEntity(null));

        quoteService.createQuote(paymentDetails);
    }

    @Test
    public void shouldUpdateQuoteWithGivenFields() {
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        when(quoteRepository.findById(any())).thenReturn(getQuote());
        when(quoteRepository.save(any())).thenReturn(quote);

        Quote actualQuote = quoteService.updateQuote(quote);

        assertNotNull(actualQuote);
        assertThat(actualQuote.getId(), is("test-id"));
        assertThat(actualQuote.getTransferWiseQuoteId(), is("test-transfer-wise-id"));
    }

    @Test(expected = QuoteNotFoundException.class)
    public void shouldThrowExceptionWhileUpdatingQuoteWhenIdIsInvalid() {
        when(quoteRepository.findById("test-id")).thenThrow(QuoteNotFoundException.class);
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        quoteService.updateQuote(quote);
    }

    @Test
    public void shouldDeleteQuote() {
        doNothing().when(quoteRepository).deleteById("test-id");
        quoteService.deleteQuote("test-id");
    }

}
