package com.mbyte.easy.webscoket.consts;

/**
 * @program: easy
 * @description: WebScoket常量配置
 * @author: 王震
 * @create: 2019-05-06 10:11
 **/
public class GlobalConsts {
    public static final String TOPIC = "/topic/greetings";

    public static final String ENDPOINT = "/gs-guide-websocket";

    public static final String APP_PREFIX = "/app";

    public static final String HELLO_MAPPING = "/hello";

    //点对点消息推送地址前缀
    public static final String P2PPUSHBASEPATH = "/user";
    //点对点消息推送地址后缀,最后的地址为/user/用户识别码/msg
    public static final String P2PPUSHPATH = "/msg";
}
