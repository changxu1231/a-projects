package com.mbyte.easy.config;

import com.mbyte.easy.webscoket.consts.GlobalConsts;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: easy
 * @description: Webscoket配置类
 * @author: 王震
 * @create: 2019-05-06 10:12
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", GlobalConsts.P2PPUSHBASEPATH);
        config.setUserDestinationPrefix(GlobalConsts.P2PPUSHBASEPATH);
        config.setApplicationDestinationPrefixes(GlobalConsts.APP_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //定义一对一推送的时候前缀
        registry.addEndpoint(GlobalConsts.ENDPOINT)
                .setAllowedOrigins("*")
                .withSockJS();
    }

}
