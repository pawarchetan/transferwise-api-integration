package com.wevat.service;

import com.wevat.model.dto.PaymentDetails;
import com.wevat.model.Quote;

import java.util.List;

public interface QuoteService {

    Quote getQuoteById(String id);

    List<Quote> getAllQuotes();

    Quote createQuote(PaymentDetails paymentDetails);

    Quote updateQuote(Quote quote);

    void deleteQuote(String id);
}

