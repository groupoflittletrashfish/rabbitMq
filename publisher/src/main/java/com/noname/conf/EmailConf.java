package com.noname.conf;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/13.
 *
 * 直连交换机的配置
 */
@Configuration
public class EmailConf {

    @Bean
    DirectExchange emailExchange() {
        return new DirectExchange("emailExchange");
    }
}
