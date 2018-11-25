package com.wevat.model.dto;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserDetailsTest {

    private UserDetails userDetails;

    @Before
    public void setUp() {
        userDetails = new UserDetails();
    }

    @Test
    public void shouldTestUserDetailsSetterAndGetters() {
        userDetails.setFirstName("test-first-name");
        userDetails.setLastName("test-last-name");
        userDetails.setPayoutCurrency("AUD");
        assertThat(userDetails.getFirstName(), is("test-first-name"));
        assertThat(userDetails.getLastName(), is("test-last-name"));
        assertThat(userDetails.getPayoutCurrency(), is("AUD"));
    }
}
