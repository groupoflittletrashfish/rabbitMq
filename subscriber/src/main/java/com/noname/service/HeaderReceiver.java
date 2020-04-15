package com.noname.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/15.
 */

@Component
public class HeaderReceiver {


    @RabbitListener(queues = "headerQueue1")
    public void receiver1(String msg) {
        System.out.println("receiver1：" + msg);
    }

    @RabbitListener(queues = "headerQueue2")
    public void receiver2(String msg) {
        System.out.println("receiver2：" + msg);
    }

    @RabbitListener(queues = "headerQueue3")
    public void receiver3(String msg) {
        System.out.println("receiver3：" + msg);
    }

    @RabbitListener(queues = "headerQueue4")
    public void receiver4(String msg) {
        System.out.println("receiver4：" + msg);
    }
}
