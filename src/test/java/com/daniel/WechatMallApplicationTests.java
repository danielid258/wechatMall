package com.daniel;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatMallApplicationTests {

    @Test
    public void contextLoads() {
        log.debug("debug .... ");
        log.error("error .... ");
        log.info("info .... ");

        String name = "jack";
        int age = 10;
        log.info("name:{},age:{}", name, age);
    }

}
