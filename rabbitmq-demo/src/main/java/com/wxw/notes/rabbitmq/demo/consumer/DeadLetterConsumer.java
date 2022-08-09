package com.wxw.notes.rabbitmq.demo.consumer;

import com.rabbitmq.client.Channel;
import com.wxw.notes.rabbitmq.demo.config.DeadLetterConfig;
import com.wxw.notes.rabbitmq.demo.config.DelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wuxiongwei
 * @date 2022/8/3 16:27
 * @Description
 */
@Slf4j
@Component
public class DeadLetterConsumer {

    /**
     * 订单队列消费者
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = DeadLetterConfig.ORDER_QUEUE)
    public void orderConsumer(Message message, Channel channel) throws IOException {
        log.info("订单队列消费者接收到消息：{}", message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicNack(deliveryTag,false,false);
    }

    /**
     * 死信队列消费者
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = DeadLetterConfig.DEAD_QUEUE)
    public void deadConsumer(Message message, Channel channel) throws IOException {
        log.info("死信队列消费者接收到消息：{}", message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // todo 订单业务处理，判断改订单状态是否已支付，如果未支付，删除该订单，已支付则不做操作
        channel.basicAck(deliveryTag,false);
    }


    /**
     * 延时队列消费者
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = DelayConfig.DELAY_QUEUE)
    public void delayConsumer(Message message, Channel channel) throws IOException {
        log.info("延时队列消费者接收到消息：{}", message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // todo 订单业务处理，判断改订单状态是否已支付，如果未支付，删除该订单，已支付则不做操作
        channel.basicAck(deliveryTag,false);
    }
}
