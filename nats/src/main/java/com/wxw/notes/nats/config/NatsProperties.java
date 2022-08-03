package com.wxw.notes.nats.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件中 nats 相关配置
 */
@Component
@ConfigurationProperties(prefix = "nats")
@Data
public class NatsProperties {

    private String natsUrls;

    private String token = null;

    private int maxReconnect = 60;

    private int reconnectWait = 2;

    private int connectionTimeout = 2;

    private String userName;

    private String password;

}
