package com.noname.conf;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/14.
 * 主题交换机-演示
 */
@Configuration
public class BlobConf {

    @Bean
    TopicExchange blogExchange() {
        return new TopicExchange("blogExchange");
    }
}
