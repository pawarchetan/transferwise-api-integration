package com.wevat.util;

import com.wevat.model.Quote;
import com.wevat.model.User;
import com.wevat.model.dto.TransferWiseQuote;

public class QuoteMapper {

    public static Quote map(TransferWiseQuote transferWiseQuote, User user) {
        Quote quote = new Quote();
        quote.setTransferWiseQuoteId(transferWiseQuote.getId());
        quote.setSource(transferWiseQuote.getSource());
        quote.setTarget(transferWiseQuote.getTarget());
        quote.setSourceAmount(transferWiseQuote.getSourceAmount());
        quote.setTargetAmount(transferWiseQuote.getTargetAmount());
        quote.setFee(transferWiseQuote.getFee());
        quote.setRate(transferWiseQuote.getRate());
        quote.setUser(user);
        return quote;
    }
}
