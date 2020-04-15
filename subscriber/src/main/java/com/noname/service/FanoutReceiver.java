package com.noname.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/14.
 *
 * 扇形交换机-演示，对应的配置文件：FanoutConf
 */
@Component
public class FanoutReceiver {

    @RabbitListener(queues = "fanoutQueue1")
    public void receiver1(String msg) {
        System.out.println("队列1:" + msg);
    }


    @RabbitListener(queues = "fanoutQueue2")
    public void receiver2(String msg) {
        System.out.println("队列2:" + msg);
    }

}
