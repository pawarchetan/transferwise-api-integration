package com.wevat.model.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TransferWiseQuoteTest {

    private TransferWiseQuote transferWiseQuote;

    @Before
    public void setUp() {
        transferWiseQuote = new TransferWiseQuote();
    }

    @Test
    public void shouldTestTransferWiseQuoteGetterAndSetters() {
        transferWiseQuote.setId("test-id");
        transferWiseQuote.setFee(new BigDecimal(5.25));
        transferWiseQuote.setRate(new BigDecimal(66.66));
        transferWiseQuote.setSource("GBP");
        transferWiseQuote.setTarget("AUD");
        transferWiseQuote.setSourceAmount(new BigDecimal(12.22));
        transferWiseQuote.setTargetAmount(new BigDecimal(75.00));

        assertThat(transferWiseQuote.getId(), is("test-id"));
        assertThat(transferWiseQuote.getFee(), is(new BigDecimal(5.25)));
        assertThat(transferWiseQuote.getRate(), is(new BigDecimal(66.66)));
        assertThat(transferWiseQuote.getSource(), is("GBP"));
        assertThat(transferWiseQuote.getTarget(), is("AUD"));
        assertThat(transferWiseQuote.getSourceAmount(), is(new BigDecimal(12.22)));
        assertThat(transferWiseQuote.getTargetAmount(), is(new BigDecimal(75.00)));

    }
}
