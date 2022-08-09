package com.wxw.notes.rabbitmq.demo.consumer;

import com.wxw.notes.rabbitmq.demo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuxiongwei
 * @date 2022/7/28 10:32
 * @Description 五种常用消息模式-消费者，注意此处基于配置消息确认方式为非手动
 */
@Slf4j
@Component
public class RabbitMqConsumer {

    /**
     * helloWorld 模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.HELLO_WORLD_QUEUE)
    public void helloWorldConsumer(String message) {
        log.info("消费者接收到消息：{}", message);
    }

    /**
     * work 模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.WORK_QUEUE)
    public void workConsumer1(String message) {
        log.info("工作队列模式消费者1接收到消息：{}", message);
    }

    /**
     * work 模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.WORK_QUEUE)
    public void workConsumer2(String message) {
        log.info("工作队列模式消费者2接收到消息：{}", message);
    }

    /**
     * 发布订阅模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.FANOUT_QUEUE1)
    public void fanoutConsumer1(String message) {
        log.info("发布订阅模式消费者1接收到消息：{}", message);
    }

    /**
     * 发布订阅模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.FANOUT_QUEUE2)
    public void fanoutConsumer2(String message) {
        log.info("发布订阅模式消费者2接收到消息：{}", message);
    }


    /**
     * 路由模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.DIRECT_QUEUE1)
    public void directConsumer1(String message) {
        log.info("路由模式消费者1接收到消息：{}", message);
    }

    /**
     * 路由模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.DIRECT_QUEUE2)
    public void directConsumer2(String message) {
        log.info("路由模式消费者2接收到消息：{}", message);
    }


    /**
     * 通配符模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE1)
    public void topicConsumer1(String message) {
        log.info("通配符模式消费者1接收到消息：{}", message);
    }

    /**
     * 通配符模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.TOPIC_QUEUE2)
    public void topicConsumer2(String message) {
        log.info("通配符模式消费者2接收到消息：{}", message);
    }


}
