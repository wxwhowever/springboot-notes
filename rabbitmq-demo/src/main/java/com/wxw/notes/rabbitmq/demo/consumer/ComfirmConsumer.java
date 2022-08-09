package com.wxw.notes.rabbitmq.demo.consumer;

import com.rabbitmq.client.Channel;
import com.wxw.notes.rabbitmq.demo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wuxiongwei
 * @date 2022/7/28 10:32
 * @Description 消费方消息确认
 */
@Slf4j
@Component
public class ComfirmConsumer {

    /**
     * 路由模式
     *
     * @param message 消息内容
     */
    @RabbitListener(queues = RabbitMqConfig.DIRECT_QUEUE1)
    public void fanoutConsumer1(Message message, Channel channel) throws IOException {
        log.info("发布订阅模式消费者1接收到消息：{}", message);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 手动进行消息确认
        channel.basicAck(deliveryTag,false);

        channel.basicNack(deliveryTag,false,false);

        channel.basicReject(deliveryTag,false);
    }

}
