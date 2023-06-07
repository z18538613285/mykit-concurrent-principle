package io.learning.websocket.server;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/server/{userId}")
public class WebSocketServerDemo {
 	static Logger log = LoggerFactory.getLogger(WebSocketServerDemo.class);
 	
	 /**
	 * 连接成功
	 *
	 * @param session
	 */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param
     */
    @OnMessage
    public void onMsg(Session session, String msg) throws IOException {
    	// 接收客户端发送的消息
    	log.info("WebSocket Client: {}", msg);
    	// 给客户端发送一条消息
    	session.getBasicRemote().sendText("WebSocket Server: " + "Time and tide wait for no man.");    	
    }
}

