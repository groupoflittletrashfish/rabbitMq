package com.noname.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/14.
 * 扇形交换机-配置
 */

@Configuration
public class FanoutConf {

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    @Bean
    Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }

    @Bean
    Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }

    /**
     * 扇形交换机不需要routingkey
     *
     * @return
     */
    @Bean
    Binding bindFanout1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }


    @Bean
    Binding bindFanout2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
