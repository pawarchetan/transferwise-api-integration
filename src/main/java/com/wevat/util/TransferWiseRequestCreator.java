package com.wevat.util;

import com.wevat.model.dto.TransferWiseQuoteRequest;

import java.math.BigDecimal;

public class TransferWiseRequestCreator {
    public static TransferWiseQuoteRequest create(String profileId, String payoutCurrency, BigDecimal paymentAmount) {
        TransferWiseQuoteRequest transferWiseQuoteRequest = new TransferWiseQuoteRequest();
        transferWiseQuoteRequest.setProfileId(profileId);
        transferWiseQuoteRequest.setTarget(payoutCurrency);
        transferWiseQuoteRequest.setTargetAmount(paymentAmount);
        return transferWiseQuoteRequest;
    }
}
