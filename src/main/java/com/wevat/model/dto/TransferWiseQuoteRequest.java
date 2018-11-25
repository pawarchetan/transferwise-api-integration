package com.wevat.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferWiseQuoteRequest {
    private String profileId;
    private String target;
    private BigDecimal targetAmount;
    private String source = "GBP";
    private String rateType = "FIXED";
    private String type = "BALANCE_PAYOUT";
}
