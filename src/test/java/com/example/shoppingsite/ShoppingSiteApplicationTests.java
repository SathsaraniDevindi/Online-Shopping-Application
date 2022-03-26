package com.example.shoppingsite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingSiteApplication.class)
public class ShoppingSiteApplicationTests {

    @Test
    public void contextLoads() {
        String main = "Main Application";
        Assert.assertEquals("Main Application",main);
    }

}

