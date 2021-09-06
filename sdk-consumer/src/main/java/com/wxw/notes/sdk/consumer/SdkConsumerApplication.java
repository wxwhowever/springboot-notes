package com.wxw.notes.sdk.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wuxiongwei
 * @date 2021/9/6 10:16
 * @Description
 */
@SpringBootApplication
@ComponentScan({"com.wxw.notes.sdk.consumer","com.wxw.notes.sdk.provider"})
public class SdkConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdkConsumerApplication.class);
    }
}
