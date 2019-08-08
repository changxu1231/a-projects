package com.mbyte.easy.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @program: easy
 * @description: 定义生产者
 * @author: 王震
 * @create: 2019-05-05 11:29
 **/
//@Configuration
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    /**
     * 每5S执行一次
     */
    @Scheduled(fixedRate = 5000, initialDelay = 3000)
    public void send() {
        //发送队列消息
        this.jmsMessagingTemplate.convertAndSend(this.queue, "生产者：http://www.byte.ac.cn");
        System.out.println("生产者：生产者：http://www.byte.ac.cn");
        //发送订阅消息
        this.jmsMessagingTemplate.convertAndSend(this.topic, "生产者生产的订阅/发布消息：生产者：http://www.byte.ac.cn");
        System.out.println("生产者：生产的订阅/发布消息：http://www.byte.ac.cn");
    }

}
