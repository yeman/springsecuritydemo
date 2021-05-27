package com.yjt.springcloud.demo01;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testBCryptPasswordEncoder(){
        PasswordEncoder passwordEncoder = wac.getBean(PasswordEncoder.class);
        String encode = passwordEncoder.encode("root");
        log.info("加密后密码为{}",encode);
    }

    @Test
    public void testLogin() throws Exception {
        MvcResult result = this.mvc.perform(request(GET,"/login")).andReturn();
        log.info(ReflectionToStringBuilder.toString(result.getResponse(), ToStringStyle.MULTI_LINE_STYLE));
    }

}
