package com.ly.controller;

import com.ly.utils.WebSocketUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: ChatRoomController
 * @Author: lin
 * @Description: 聊天室
 * @Date: 2019-06-10 15:34
 * @Version: 1.0
 */
@RestController
@ServerEndpoint("/chatRoom/{username}")
public class ChatRoomController {

    @OnOpen
    public void open(@PathParam("username") String username, Session session){
        WebSocketUtils.LIVE_SESSION.put(username,session);
        System.out.println("欢迎用户["+username+"]来到聊天室！");
        WebSocketUtils.sendMessageAll("欢迎用户["+username+"]来到聊天室！");
    }

    @OnMessage
    public void message(@PathParam("username") String username,String message){
        WebSocketUtils.sendMessageAll("用户["+username+"]:"+message);
    }

    @OnClose
    public void close(@PathParam("username") String username,Session session){

        WebSocketUtils.LIVE_SESSION.remove(username);

        WebSocketUtils.sendMessageAll("用户["+username+"]离开聊天室！");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void error(Session session,Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/chatRoom/{sender}/to/{receive}")
    public void message(@PathVariable("sender")String sender,@PathVariable("receive")String receive,String message){
        WebSocketUtils.sendMessage(WebSocketUtils.LIVE_SESSION.get(receive),"["+sender+"] to ["+receive+"]:"+message);
    }
}
