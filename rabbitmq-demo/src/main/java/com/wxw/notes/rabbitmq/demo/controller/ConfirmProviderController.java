package com.wxw.notes.rabbitmq.demo.controller;

import com.wxw.notes.rabbitmq.demo.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wuxiongwei
 * @date 2022/7/29 8:23
 * @Description
 */
@RestController
@Slf4j
@RequestMapping("/confirm")
@RequiredArgsConstructor
public class ConfirmProviderController {

    private final RabbitTemplate rabbitTemplate;


    @RequestMapping("/routingSend")
    public void routingSend() {
        String routingKeyMessage1 = "routing Message 1";
        log.info("路由模式生产者发送消息 :{}", routingKeyMessage1);

        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY1, routingKeyMessage1, new CorrelationData(UUID.randomUUID().toString()));
    }

}
