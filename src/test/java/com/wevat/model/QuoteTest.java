package com.wevat.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuoteTest {

    private Quote quote;

    @Before
    public void setUp() {
        quote = new Quote();
    }

    @Test
    public void shouldTestTransferWiseQuoteIdGetterAndSetter() {
        quote.setTransferWiseQuoteId("test-transfer-wise-id");
        assertThat(quote.getTransferWiseQuoteId(), is("test-transfer-wise-id"));
    }

    @Test
    public void shouldTestSourceAndTargetSetterAndGetterProperty() {
        quote.setSource("test-source");
        quote.setTarget("test-target");
        assertThat(quote.getSource(), is("test-source"));
        assertThat(quote.getTarget(), is("test-target"));
    }

    @Test
    public void shouldTestSourceAndTargetAmountProperty() {
        quote.setSourceAmount(new BigDecimal(300.00));
        quote.setTargetAmount(new BigDecimal(500.00));
        assertThat(quote.getSourceAmount(), is(new BigDecimal(300.00)));
        assertThat(quote.getTargetAmount(), is(new BigDecimal(500.00)));
    }

    @Test
    public void shouldTestRateAndFeeProperty() {
        quote.setRate(new BigDecimal(33.56));
        quote.setFee(new BigDecimal(1.45));
        assertThat(quote.getRate(), is(new BigDecimal(33.56)));
        assertThat(quote.getFee(), is(new BigDecimal(1.45)));
    }
}
