package com.wevat.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDetails {
    private String userId;
    private BigDecimal payAmount;
}
