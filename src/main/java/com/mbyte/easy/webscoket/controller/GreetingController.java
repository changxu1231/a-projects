package com.mbyte.easy.webscoket.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.mbyte.easy.webscoket.consts.GlobalConsts;
import com.mbyte.easy.webscoket.vo.ClientMessage;
import com.mbyte.easy.webscoket.vo.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

/**
 * @program: easy
 * @description: webscoket测试controller
 * @author: 王震
 * @create: 2019-05-06 10:26
 **/
@Controller
@RequestMapping("/admin/greeting")
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;


    @RequestMapping
    public String index(Model model) {

        return "webscoket/greeting";
    }

    @RequestMapping("/index2")
    public String index2(Model model) {

        return "webscoket/greeting2";
    }

    @MessageMapping(GlobalConsts.HELLO_MAPPING)
    @SendTo(GlobalConsts.TOPIC)
    public ServerMessage greeting(ClientMessage message) throws Exception {
        // 模拟延时，以便测试客户端是否在异步工作
//        Thread.sleep(1000);
        template.convertAndSendToUser(message.getId() + "", GlobalConsts.P2PPUSHPATH, JSON.toJSON(new ServerMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!")));
        return new ServerMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


}
