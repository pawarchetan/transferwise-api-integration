package com.wevat;

import com.wevat.config.BeanConfigTest;
import com.wevat.config.SwaggerConfigTest;
import com.wevat.controller.QuoteControllerTest;
import com.wevat.controller.UserControllerTest;
import com.wevat.model.QuoteTest;
import com.wevat.model.UserTest;
import com.wevat.model.dto.PaymentDetailsTest;
import com.wevat.model.dto.TransferWiseQuoteRequestTest;
import com.wevat.model.dto.TransferWiseQuoteTest;
import com.wevat.model.dto.UserDetailsTest;
import com.wevat.service.impl.QuoteServiceImplTest;
import com.wevat.service.impl.UserServiceImplTest;
import com.wevat.util.QuoteMapperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        QuoteTest.class,
        UserTest.class,
        PaymentDetailsTest.class,
        UserDetailsTest.class,
        TransferWiseQuoteTest.class,
        TransferWiseQuoteRequestTest.class,
        UserServiceImplTest.class,
        QuoteServiceImplTest.class,
        QuoteMapperTest.class,
        SwaggerConfigTest.class,
        BeanConfigTest.class,
        UserControllerTest.class,
        QuoteControllerTest.class
})
public class TestSuite {
}
