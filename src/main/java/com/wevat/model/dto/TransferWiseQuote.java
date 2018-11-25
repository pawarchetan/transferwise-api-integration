package com.wevat.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferWiseQuote {
    private String id;
    private String source;
    private String target;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
    private BigDecimal rate;
    private BigDecimal fee;
}
