package com.wxw.notes.rabbitmq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wuxiongwei
 * @date 2022/7/11 16:01
 * @Description
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RabbitMqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class);
    }
}
