package com.wevat.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void shouldTestFirstAndLastNameSetterAndGetter() {
        user.setFirstName("test-first-name");
        user.setLastName("test-last-name");
        assertThat(user.getFirstName(), is("test-first-name"));
    }

    @Test
    public void shouldTestPayoutCurrencySetterAndGetter() {
        user.setPayoutCurrency("AUD");
        assertThat(user.getPayoutCurrency(), is("AUD"));
    }
}
