package com.springboot.services.producer;

import com.springboot.services.producer.rest.HelloController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;

public class MvcMockTest {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new HelloController());
    }

    @Test
    public void testMethod() {
    }
}