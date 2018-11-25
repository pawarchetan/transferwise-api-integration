package com.wevat.controller;

import com.wevat.model.Quote;
import com.wevat.model.dto.PaymentDetails;
import com.wevat.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/wevat/api/v1/quotes")
@Api(description = "Operations on Quote object.")
public class QuoteController {

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get quote by Id", response = Quote.class)
    public Quote getQuoteById(@ApiParam(value = "Id", required = true) @PathVariable(value = "id") String id) {
        return quoteService.getQuoteById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all quotes", response = Quote.class)
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create Quote and save it to DB.", response = Quote.class, consumes = "application/json", produces =
            "application/json")
    public Quote createQuote(@RequestBody PaymentDetails paymentDetails) {
        return quoteService.createQuote(paymentDetails);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update quote", response = Quote.class, consumes = "application/json", produces = "application/json")
    public Quote updateQuote(@RequestBody Quote quote) {
        return quoteService.updateQuote(quote);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete quote with specified Id")
    public void deleteQuote(@ApiParam(value = "Id", required = true) @PathVariable(value = "id") String id) {
        quoteService.deleteQuote(id);
    }

}
