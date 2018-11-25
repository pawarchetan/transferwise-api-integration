package com.wevat.model.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PaymentDetailsTest {

    private PaymentDetails paymentDetails;

    @Before
    public void setUp() {
        paymentDetails = new PaymentDetails();
    }

    @Test
    public void shouldTestPaymentDetailsSetterAndGetter() {
        paymentDetails.setPayAmount(new BigDecimal(33.00));
        paymentDetails.setUserId("test-user-id");
        assertThat(paymentDetails.getUserId(), is("test-user-id"));
        assertThat(paymentDetails.getPayAmount(), is(new BigDecimal(33.00)));
    }
}
