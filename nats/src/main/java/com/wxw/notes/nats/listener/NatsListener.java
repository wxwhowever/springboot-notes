package com.wxw.notes.nats.listener;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

/**
 * NatsListener 连接监听
 */
public class NatsListener implements ConnectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *  监听 nats 连接状态 ，并进行消息订阅
     * @param conn
     * @param type
     */
    @Override
    public void connectionEvent(Connection conn, Events type) {
        LOGGER.info(String.format("nats connection status: %s", conn.getStatus()));
        if(Connection.Status.CONNECTED.equals(conn.getStatus())){
            LOGGER.info("subscribe topic");
            // 连接成功后进行消息订阅
            subscribe(conn);
        }
    }

    /**
     * 异步订阅
     * @param conn  连接
     */
    public void subscribe(Connection conn){

        Dispatcher dispatcher = conn.createDispatcher(msg -> {
            LOGGER.info("Received message {}, on subject {}", new String(msg.getData(), StandardCharsets.UTF_8), msg.getSubject());
        });

        //订阅 subject 为 NATS_TEST 的消息
        dispatcher.subscribe("NATS_TEST");

        // 不关闭connection，方便测试多次接收消息
    }
}
