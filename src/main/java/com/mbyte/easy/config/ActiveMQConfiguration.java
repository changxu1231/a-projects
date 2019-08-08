package com.mbyte.easy.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @program: easy
 * @description: ActiveMQ配置类
 * @author: 王震
 * @create: 2019-05-05 11:26
 **/
@Configuration
@EnableJms
public class ActiveMQConfiguration {

    /**
     * 定义点对点队列
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }
    /**
     * 定义一个主题
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sample.topic");
    }

}
