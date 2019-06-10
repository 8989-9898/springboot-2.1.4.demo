package com.ly.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.utils
 * @ClassName: WebSocketUtils
 * @Author: lin
 * @Description: websocket的工具类和操作类
 * @Date: 2019-06-10 15:27
 * @Version: 1.0
 */
public class WebSocketUtils {

    /**
     * 模拟存储用户登陆信息
     */
    public static final Map<String, Session> LIVE_SESSION=new ConcurrentHashMap<>();

    /**
     * 群发信息
     * @param message
     */
    public static void sendMessageAll(String message){
        LIVE_SESSION.forEach((sessionId,session)->sendMessage(session,message));
    }

    /**
     * 发送信息给指定的用户
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if (session==null) {
            return;
        }
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        if (basicRemote==null) {
            return;
        }
        try {
            basicRemote.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
