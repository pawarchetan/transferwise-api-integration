package com.wevat.util;

import com.wevat.model.Quote;
import com.wevat.model.User;
import com.wevat.model.dto.TransferWiseQuote;
import org.junit.Test;

import java.math.BigDecimal;

import static com.wevat.TestDataSetup.getTransferWiseQuote;
import static com.wevat.TestDataSetup.getUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class QuoteMapperTest {

    @Test
    public void shouldMapTransferWiseQuoteToQuote() {
        TransferWiseQuote transferWiseQuote = getTransferWiseQuote();
        User user = getUser().isPresent() ? getUser().get() : new User();
        Quote actualQuote = QuoteMapper.map(transferWiseQuote, user);

        assertNotNull(actualQuote);
        assertThat(actualQuote.getTransferWiseQuoteId(), is("test-transferWise-id"));
        assertThat(actualQuote.getTarget(), is("AUD"));
        assertThat(actualQuote.getTargetAmount(), is(new BigDecimal(200.00)));
        assertThat(actualQuote.getSource(), is("GBP"));
        assertThat(actualQuote.getSourceAmount(), is(new BigDecimal(100.00)));
        assertThat(actualQuote.getRate(), is(new BigDecimal(10.00)));
        assertThat(actualQuote.getFee(), is(new BigDecimal(2.00)));
        assertEquals(actualQuote.getUser(), user);
    }
}
