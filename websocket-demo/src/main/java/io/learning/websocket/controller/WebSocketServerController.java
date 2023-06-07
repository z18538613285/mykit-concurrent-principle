package io.learning.websocket.controller;

import cn.hutool.core.util.ObjectUtil;
import io.learning.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.io.IOException;

@RestController
@RequestMapping("/webSocket")
public class WebSocketServerController {
    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/serverToClient")
    public ResultVo sendServerToClient(String login) throws IOException {
        System.out.println("求职者给招聘者发送简历操作..............");
        // 判断用户是否在线
        Session session = webSocketServer.checkIsOnline(login);
        if(ObjectUtil.isNotNull(session)){
            webSocketServer.sendMessage("求职者给招聘者发送简历,招聘者已接收",session);
        }
        return ResultVoUtil.success();
    }
}
