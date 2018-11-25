package com.wevat;

import com.wevat.model.Quote;
import com.wevat.model.User;
import com.wevat.model.dto.PaymentDetails;
import com.wevat.model.dto.TransferWiseQuote;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TestDataSetup {
    public static Optional<User> getUser() {
        User user = new User();
        user.setId("test-id");
        user.setFirstName("test-first-name");
        user.setLastName("test-last-name");
        user.setPayoutCurrency("AUD");
        Set<Quote> quoteSet = new HashSet<>(getQuotes());
        user.setQuotes(quoteSet);
        return Optional.of(user);
    }

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user = getUser().isPresent() ? getUser().get() : new User();
        users.add(user);
        return users;
    }

    public static Optional<Quote> getQuote() {
        Quote quote = new Quote();
        quote.setId("test-id");
        quote.setTarget("AUD");
        quote.setTransferWiseQuoteId("test-transfer-wise-id");
        return Optional.of(quote);
    }

    public static List<Quote> getQuotes() {
        List<Quote> quotes = new ArrayList<>();
        Quote quote = getQuote().isPresent() ? getQuote().get() : new Quote();
        quotes.add(quote);
        return quotes;
    }

    public static PaymentDetails getPaymentDetails() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setUserId("test-user-id");
        paymentDetails.setPayAmount(new BigDecimal(88.88));
        return paymentDetails;
    }

    public static TransferWiseQuote getTransferWiseQuote() {
        TransferWiseQuote transferWiseQuote = new TransferWiseQuote();
        transferWiseQuote.setId("test-transferWise-id");
        transferWiseQuote.setTarget("AUD");
        transferWiseQuote.setTargetAmount(new BigDecimal(200.00));
        transferWiseQuote.setSource("GBP");
        transferWiseQuote.setSourceAmount(new BigDecimal(100.00));
        transferWiseQuote.setRate(new BigDecimal(10.00));
        transferWiseQuote.setFee(new BigDecimal(2.00));
        return transferWiseQuote;
    }

    public static ResponseEntity getResponseEntity(Object o) {
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
