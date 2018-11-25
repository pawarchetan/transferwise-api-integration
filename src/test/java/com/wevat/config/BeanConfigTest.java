package com.wevat.config;

import com.wevat.configuration.BeanConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BeanConfigTest {

    private BeanConfig beanConfig;

    @Before
    public void setUp() {
        beanConfig = new BeanConfig();
    }

    @Test
    public void shouldCreateRestTemplateBean() {
        assertNotNull(beanConfig.restTemplate());
    }

    @Test
    public void shouldCreateTransferWiseRequestInterceptorBean() {
        assertNotNull(beanConfig.transferWiseRequestInterceptor());
    }
}
