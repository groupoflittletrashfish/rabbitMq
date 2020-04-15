package com.noname.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/4/13.
 * <p>
 * 直连交换机的配置
 */
@Configuration
public class EmailConf {

    @Bean
    DirectExchange emailExchange() {
        return new DirectExchange("emailExchange");
    }

    /**
     * 配置队列信息
     *
     * @return 队列
     */
    @Bean
    Queue emailQueue() {
        return new Queue("emailQueue");
    }


    /**
     * 绑定队列和交换机，即确认哪个交换机下有哪些队列，
     * with("emailRouting") 代表的是路由，这个值是发送端发送时传入的路由值
     *
     * @return 绑定对象
     */
    @Bean
    Binding bindEmail() {
        return BindingBuilder.bind(emailQueue()).to(emailExchange()).with("emailRouting");
    }
}
