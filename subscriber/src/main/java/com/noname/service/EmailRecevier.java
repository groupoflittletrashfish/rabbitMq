package com.noname.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/14.
 */
@Component
public class EmailRecevier {


    /**
     * 直连交换机-演示，对应的配置文件：EmailConf
     * 注解：queues = "emailQueue" 是指队列名，即在conf中给队列的命名
     *
     * @param msg
     */
    @RabbitListener(queues = "emailQueue")
    public void receiver(String msg) {
        System.out.println("收到的消息为:" + msg);
    }
}
