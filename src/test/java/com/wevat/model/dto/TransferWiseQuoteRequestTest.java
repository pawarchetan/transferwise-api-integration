package com.wevat.model.dto;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TransferWiseQuoteRequestTest {

    private TransferWiseQuoteRequest transferWiseQuoteRequest;

    @Before
    public void setUp() {
        transferWiseQuoteRequest = new TransferWiseQuoteRequest();
    }

    @Test
    public void shouldTestTransferWiseQuoteRequestSetterAndGetters() {
        transferWiseQuoteRequest.setProfileId("test-profile-id");
        transferWiseQuoteRequest.setTarget("AUD");
        transferWiseQuoteRequest.setTargetAmount(new BigDecimal(55.33));
        assertThat(transferWiseQuoteRequest.getProfileId(), is("test-profile-id"));
        assertThat(transferWiseQuoteRequest.getTarget(), is("AUD"));
        assertThat(transferWiseQuoteRequest.getTargetAmount(), is(new BigDecimal(55.33)));

    }
}
