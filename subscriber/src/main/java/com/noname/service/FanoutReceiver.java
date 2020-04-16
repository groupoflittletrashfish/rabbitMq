package com.noname.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2020/4/14.
 * <p>
 * 扇形交换机-演示，对应的配置文件：FanoutConf
 */
@Component
public class FanoutReceiver {

    @RabbitListener(queues = "fanoutQueue1")
    public void receiver1(String msg, Channel channel, Message message) throws IOException {
        try {
            System.out.println("队列1:" + msg);
            //确认消息，由于是改成了手动确认模式，需要在程序的最后一行加入确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            //否认消息并重新丢回队列中,第二个参数：是否批量. true：将一次性拒绝所有小于deliveryTag的消息。第三个参数（true:被拒绝的是否重新入队列。）
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }


    @RabbitListener(queues = "fanoutQueue2")
    public void receiver2(String msg, Channel channel, Message message) {
        try {
            System.out.println("队列2:" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
