package com.wxw.notes.rabbitmq.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuxiongwei
 * @date 2022/8/3 10:25
 * @Description 配置发送方confirm 消息确认机制
 */
@Slf4j
@Configuration
public class RabbitTemplateConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 确认消息送到交换机回调
        // 如果消息没有到 exchange,则 confirm 回调,ack=false; 如果消息到达exchange,则confirm回调,ack=true
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    log.info("消息发送交换机成功：correlationData：{},ack：{},cause：{}",correlationData,ack,cause);
                }else{
                    log.info("消息发送交换机失败：correlationData：{},ack：{},cause：{}",correlationData,ack,cause);
                }
            }
        });

        // 确认消息送到队列回调
        // 如果exchange到queue成功,则不回调return;如果exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("确认消息送到队列结果：");
                log.info("发送消息：{}", message);
                log.info("回应码：{}", replyCode);
                log.info("回应信息：{}", replyText);
                log.info("交换机：{}", exchange);
                log.info("路由键：{}", routingKey);
            }
        });
        return rabbitTemplate;
    }

}
