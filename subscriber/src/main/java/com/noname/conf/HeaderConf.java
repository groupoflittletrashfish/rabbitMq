package com.noname.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    Queue headerQueue1() {
        return new Queue("headerQueue1");
    }

    @Bean
    Queue headerQueue2() {
        return new Queue("headerQueue2");
    }

    @Bean
    Queue headerQueue3() {
        return new Queue("headerQueue3");
    }

    @Bean
    Queue headerQueue4() {
        return new Queue("headerQueue4");
    }


    /**
     * 若头信息中同时存在指定字段，则将消息放入队列
     *
     * @return
     */
    @Bean
    Binding bindHeaderQueue1() {
        return BindingBuilder.bind(headerQueue1()).to(headersExchange()).whereAll("token", "id").exist();
    }


    /**
     * 若头信息中有任意指定字段，则将消息放入队列
     *
     * @return
     */
    @Bean
    Binding bingHeaderQueue2() {
        return BindingBuilder.bind(headerQueue2()).to(headersExchange()).whereAny("token", "id").exist();
    }


    /**
     * 若头信息中所有值配对上，则将消息放入队列
     *
     * @return
     */
    @Bean
    Binding bindHeaderQueue3() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "123");
        map.put("id", "123");
        return BindingBuilder.bind(headerQueue3()).to(headersExchange()).whereAll(map).match();
    }


    /**
     * 若头信息中任意值配对上，则将消息放入队列
     *
     * @return
     */
    @Bean
    Binding bindHeaderQueue4() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "123");
        map.put("id", "123");
        return BindingBuilder.bind(headerQueue4()).to(headersExchange()).whereAny(map).match();
    }
}
