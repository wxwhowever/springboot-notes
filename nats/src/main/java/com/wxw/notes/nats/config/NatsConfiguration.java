package com.wxw.notes.nats.config;

import com.wxw.notes.nats.listener.NatsListener;
import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;


/**
 * nats 连接配置类
 */
@Configuration
public class NatsConfiguration {

    /**
     * 注入 nats 连接
     * @param properties nats 配置文件类
     * @return  nats 连接
     * @throws IOException
     * @throws InterruptedException
     */
    @Bean(name = "natsConnection")
    public Connection natsConnection(NatsProperties properties) throws IOException, InterruptedException {
        String[] str = properties.getNatsUrls().split(",");
        Options.Builder builder = new Options.Builder()
                // 配置 nats 服务器地址
                .servers(str)
                // 配置用户名和密码
                .userInfo(properties.getUserName().toCharArray(),properties.getPassword().toCharArray())
                // nats 监听
                .connectionListener(new NatsListener())
                // 最大重连次数
                .maxReconnects(properties.getMaxReconnect())
                // 重连等待时间
                .reconnectWait(Duration.ofSeconds(properties.getReconnectWait()))
                // 连接超时时间
                .connectionTimeout(Duration.ofSeconds(properties.getConnectionTimeout()));
        if (properties.getToken() != null) {
            builder.token(properties.getToken().toCharArray());
        }
        //连接 nats
        return Nats.connect(builder.build());

    }
}
