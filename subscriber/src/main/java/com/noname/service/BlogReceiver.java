package com.noname.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/4/14.
 * 对应的配置文件：BlogConf
 *
 * 此例定义了3类博客，java,dotnet和同时关注这两类的博客
 * 首先需要监听各自的队列，而all类的队列在配置文件中使用了通配符#，
 * 发送端发送的路由为blob.java,所以匹配到了java和All的监听
 */
@Component
public class BlogReceiver {

    @RabbitListener(queues = "blogJavaQueue")
    public void receiverJava(String msg) {
        System.out.println("java:" + msg);
    }


    @RabbitListener(queues = "blogDotNetQueue")
    public void receiverDotNet(String msg) {
        System.out.println("dotNet:" + msg);
    }


    @RabbitListener(queues = "blogAllQueue")
    public void receiverAll(String msg) {
        System.out.println("all:" + msg);
    }
}
