package com.wxw.notes.rabbitmq.demo.controller;

import com.wxw.notes.rabbitmq.demo.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiongwei
 * @date 2022/7/29 8:23
 * @Description
 */
@RestController
@Slf4j
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {

    private final RabbitTemplate rabbitTemplate;


    @RequestMapping("/helloSend")
    public void helloSend() {
        String message = "helloWorld Message";
        log.info("生产者发送消息 :{}", message);
        rabbitTemplate.convertAndSend(RabbitMqConfig.HELLO_WORLD_QUEUE, message);
    }

    @RequestMapping("/workSend")
    public void workSend() {
        for (int i = 1; i <= 10; i++) {
            String message = "work Message" + i;
            log.info("工作队列模式生产者发送消息 :{}", message);
            rabbitTemplate.convertAndSend(RabbitMqConfig.WORK_QUEUE, message);
        }
    }

    @RequestMapping("/fanoutSend")
    public void fanoutSend() {
        String message = "fanout Message";
        log.info("发布订阅模式生产者发送消息 :{}", message);
        rabbitTemplate.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, "", message);
    }

    @RequestMapping("/routingSend")
    public void routingSend() {
        String routingKeyMessage1 = "routing Message 1";
        String routingKeyMessage2 = "routing Message 2";
        log.info("路由模式生产者发送消息 :{}", routingKeyMessage1);
        log.info("路由模式生产者发送消息 :{}", routingKeyMessage2);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY1, routingKeyMessage1);
        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE, RabbitMqConfig.DIRECT_ROUTING_KEY2, routingKeyMessage2);
    }

    @RequestMapping("/topicSend")
    public void topicSend() {
        String routingKeyMessage1 = "topic Message 1";
        String routingKeyMessage2 = "topic Message 2";
        String routingKeyMessage3 = "topic Message 3";
        log.info("通配符模式生产者发送消息 :{}", routingKeyMessage1);
        log.info("通配符模式生产者发送消息 :{}", routingKeyMessage2);
        log.info("通配符模式生产者发送消息 :{}", routingKeyMessage3);
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "topic.routing.key.value.1", routingKeyMessage1);
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "topic.routing.key.value.2", routingKeyMessage2);
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, "topic.routing.key.value", routingKeyMessage3);
    }
}
