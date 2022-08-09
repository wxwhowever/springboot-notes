package com.wxw.notes.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuxiongwei
 * @date 2022/7/28 10:25
 * @Description 五种常用消息模式配置文件
 */
@Configuration
public class RabbitMqConfig {

    // 简单队列模式 hello world 一个生产者一个消费者
    public static final String HELLO_WORLD_QUEUE = "hello_world";


    /**
     * ********************************** helloWorld 简单队列模式 **********************************
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_WORLD_QUEUE);
    }


    // 工作队列模式 一个生产者多个消费者
    public static final String WORK_QUEUE = "work";


    /**
     * ********************************** work 工作队列模式 **********************************
     */
    @Bean
    public Queue workQueue() {
        return new Queue(WORK_QUEUE);
    }


    /**
     * ********************************** publish/subscribe 发布订阅模式 **********************************
     */

    // publish/subscribe 发布订阅模式 队列
    public static final String FANOUT_QUEUE1 = "fanout_queue1";
    public static final String FANOUT_QUEUE2 = "fanout_queue2";
    // 发布订阅模式交换机
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    /**
     * 发布订阅模式队列1
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1);
    }

    /**
     * 发布订阅模式队列2
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE2);
    }

    /**
     * 发布订阅模式交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 发布订阅模式队列1和交换机绑定
     */
    @Bean
    public Binding bindingFanoutExchange1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    /**
     * 发布订阅模式队列2和交换机绑定
     */
    @Bean
    public Binding bindingFanoutExchange2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }


    /**
     * ********************************** routing 路由模式 **********************************
     */

    // routing 路由模式
    public static final String DIRECT_QUEUE1 = "direct_queue1";
    public static final String DIRECT_QUEUE2 = "direct_queue2";
    // 路由模式交换机
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    // 路由模式 routing key
    public static final String DIRECT_ROUTING_KEY1 = "direct.routing.key1";
    public static final String DIRECT_ROUTING_KEY2 = "direct.routing.key2";

    /**
     * 路由模式队列1
     */
    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE1);
    }

    /**
     * 路由模式队列2
     */
    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE2);
    }

    /**
     * 路由模式交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * 路由模式队列1和交换机绑定
     */
    @Bean
    public Binding bindingDirectExchange1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(DIRECT_ROUTING_KEY1);
    }

    /**
     * 路由模式队列2和交换机绑定
     */
    @Bean
    public Binding bindingDirectExchange2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(DIRECT_ROUTING_KEY2);
    }


    /**
     * ********************************** topic 通配符模式 **********************************
     */

    // topic 通配符模式
    public static final String TOPIC_QUEUE1 = "topic_queue1";
    public static final String TOPIC_QUEUE2 = "topic_queue2";
    // 通配符模式交换机
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_ROUTING_KEY1 = "topic.routing.key.*";
    public static final String TOPIC_ROUTING_KEY2 = "topic.routing.key.#";

    /**
     * 通配符模式队列1
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1);
    }

    /**
     * 通配符模式队列2
     */
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2);
    }

    /**
     * 通配符模式交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 通配符模式队列1和交换机绑定
     */
    @Bean
    public Binding bindingTopictExchange1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_ROUTING_KEY1);
    }

    /**
     * 通配符模式队列2和交换机绑定
     */
    @Bean
    public Binding bindingTopicExchange2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_ROUTING_KEY2);
    }


}
