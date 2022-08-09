package com.wxw.notes.rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxiongwei
 * @date 2022/8/3 10:25
 * @Description 死信队列配置文件
 */
@Configuration
public class DeadLetterConfig {

    // 订单队列
    public static final String ORDER_QUEUE = "order_queue";
    // 订单交换机
    public static final String ORDER_EXCHANGE = "order_exchange";


    /**
     * 订单队列
     */
    @Bean
    public Queue orderQueue() {
        Map<String, Object> map = new HashMap<>();
        // 绑定该队列到死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
//        // 消息 RoutingKey 路由模式需要设置,当前使用发布订阅模式不需要配置 routingKey
//        map.put("x-dead-letter-routing-key","routingKey");
//        // 消息3秒后过期
//        map.put("x-message-ttl",3000);
        // 设置队列的最大长度值为1
//        map.put("x-max-length", 1);
        return new Queue(ORDER_QUEUE, true, false, false, map);
    }

    /**
     * 订单交换机
     */
    @Bean
    public FanoutExchange orderExchange() {
        return new FanoutExchange(ORDER_EXCHANGE);
    }

    /**
     * 订单队列和交换机绑定
     */
    @Bean
    public Binding bindingOrderExchange() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange());
    }


    // ******************************* 死信队列 ************************************

    // 死信队列
    public static final String DEAD_QUEUE = "dead_queue";
    // 死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";


    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE);
    }

    /**
     * 死信交换机
     */
    @Bean
    public FanoutExchange deadExchange() {
        return new FanoutExchange(DEAD_EXCHANGE);
    }

    /**
     * 死信队列和死信交换机绑定
     */
    @Bean
    public Binding bindingDeadExchange() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange());
    }

}
