package com.noname.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
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

    /**
     * java类的队列，第二个参数：是否持久化
     *
     * @return
     */
    @Bean
    Queue blogJavaQueue() {
        return new Queue("blogJavaQueue", true);
    }


    /**
     * .net类的队列
     *
     * @return
     */
    @Bean
    Queue blogDotNetQueue() {
        return new Queue("blogDotNetQueue", true);
    }


    /**
     * 同时关注java和.net的队列
     *
     * @return
     */
    @Bean
    Queue blogAllQueue() {
        return new Queue("blogAllQueue", true);
    }


    @Bean
    Binding bindingToJavaQueue() {
        return BindingBuilder.bind(blogJavaQueue()).to(blogExchange()).with("blog.java");
    }

    @Bean
    Binding bindingToDotNetQueue() {
        return BindingBuilder.bind(blogDotNetQueue()).to(blogExchange()).with("blog.dotNet");
    }

    /**
     * 通配符#，匹配一个或者多个单词
     * 通配符*，只能匹配一个单词
     *
     * @return
     */
    @Bean
    Binding bindingToAlltQueue() {
        return BindingBuilder.bind(blogAllQueue()).to(blogExchange()).with("blog.#");
    }
}
