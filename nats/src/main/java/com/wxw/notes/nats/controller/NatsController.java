package com.wxw.notes.nats.controller;

import io.nats.client.Connection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author wuxiongwei
 * @date 2021/6/16 16:32
 * @Description 测试 nats 消息推送
 */
@RestController
public class NatsController {

    @Resource(name = "natsConnection")
    private Connection natsConnection;



    @RequestMapping("/publish")
    public void publish(){
        String msg = "hello nats";
        // subject 为 NATS_TEST，消息内容为 msg
        natsConnection.publish("NATS_TEST", msg.getBytes(StandardCharsets.UTF_8));
    }
}
