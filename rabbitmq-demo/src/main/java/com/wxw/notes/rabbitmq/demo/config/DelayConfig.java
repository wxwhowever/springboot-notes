package com.wxw.notes.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxiongwei
 * @date 2022/8/3 10:25
 * @Description 延时队列配置文件
 */
@Configuration
public class DelayConfig {

    // 延时队列
    public static final String DELAY_QUEUE = "delay_queue";
    // 延时交换机
    public static final String DELAY_EXCHANGE = "delay_exchange";
    public static final String DELAY_KEY = "delay_key";


    /**
     * 延时队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    /**
     * 延时交换机
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        // 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数
        return new CustomExchange(DELAY_EXCHANGE,"x-delayed-message",true,false,map);
    }

    /**
     * 延时队列和交换机绑定
     */
    @Bean
    public Binding bindingDelayExchange() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY).noargs();
    }
}
