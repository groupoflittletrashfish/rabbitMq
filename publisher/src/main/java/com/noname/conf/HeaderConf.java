package com.noname.conf;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * 头交换机配置
 */
@Configuration
public class HeaderConf {

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headerExchange");
    }
}
