package com.noname.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2020/4/13.
 */

@RestController
@RequestMapping("amqp")
public class AmqpController {

    @Resource
    private RabbitTemplate template;

    /**
     * 直连交换机-演示，相应的配置文件：EmailConf
     *
     * @param params 发送参数
     * @return 返回参数
     */
    @RequestMapping("direct")
    public String sendEmail(@RequestParam Map<String, Object> params) {
        String msg = params.get("msg").toString();
        /*
        *第一个参数：交换机的名称，在EmailConf中注册的交换机
        *第二个参数：路由Key,在EmailConf中绑定的路由
        *
        */
        template.convertAndSend("emailExchange", "emailRouting", msg);
        return "OK";
    }


    /**
     * 主题交换机-演示，相应的配置文件：BlobConf
     *
     * @param params
     * @return
     */
    @RequestMapping("topic")
    public String sendBlog(@RequestParam Map<String, Object> params) {
        String msg = params.get("msg").toString();
        /*
        *第一个参数：交换机的名称，在BlobConf中注册的交换机
        *第二个参数：路由，主题模式一般都是以.隔开，比如java类的博客
        *
        */
        template.convertAndSend("blogExchange", "blog.java", msg);
        return "OK";
    }


    /**
     * 扇形交换机-演示，对应配置：FanoutConf
     *
     * @param params
     * @return
     */
    @RequestMapping("fanout")
    public String sendFanout(@RequestParam Map<String, Object> params) {
        String msg = params.get("msg").toString();
        /*
        *第一个参数：交换机的名称，在EmailConf中注册的交换机
        *第二个参数：扇形交换机模式下不需要routingkey,因为该交换机就是下发到所有的队列中
        *
        */
        template.convertAndSend("fanoutExchange", null, msg);
        return "OK";
    }


    /**
     * 头交换机-演示,对应配置：HeaderConf
     * 类似Http请求中的请求头，也可以自定义头信息
     *
     * @param params
     * @return
     */
    @RequestMapping("header")
    public String sendHeader(@RequestParam Map<String, Object> params) {
        String msg = params.get("msg").toString();
        //设置头信息
        MessageProperties messageProperties = new MessageProperties();
        if (params.get("token") != null) {
            messageProperties.setHeader("token", params.get("token"));
        }
        if (params.get("id") != null) {
            messageProperties.setHeader("id", params.get("id"));
        }

        Message message = new Message(msg.getBytes(), messageProperties);
        /*
        *第一个参数：交换机的名称，在HeaderConf中注册的交换机
        *第二个参数: 不需要routingKey
        *
        */
        template.convertAndSend("headerExchange", null, message);
        return "OK";
    }

}
