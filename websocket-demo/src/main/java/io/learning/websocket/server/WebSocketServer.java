package io.learning.websocket.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@Service
@ServerEndpoint("/webSocket/{login}/{type}")  // login表示用户唯一标识,type表示用户类型:1.求职身份;2.面试身份
public class WebSocketServer {


    private static Log log = LogFactory.get(WebSocketServer.class);

    //当前在线连接数
    private static int onlineCount = 0;
    // 每个在线用户会创建一个WebSocketServer对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    // 存放所有在线的客户端 key为用户的唯一标识:login,value为每个会话连接
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
//    private Session session;
    // 用户login
    private String login = "";
    // 处理使用@Autowire注入为空的问题,使用静态变量处理
    private static NewsMapper newsMapper= SpringUtils.getBean(NewsMapper.class);
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("login") String login, @PathParam("type") Integer type) {
        clients.put(login, session);
//        this.session = session;
        webSocketSet.add(this);     //加入set中
        this.login = login;
        addOnlineCount();           //在线数加1
        try {
            // 查询用户未读消息数
            Integer unReadMsg=0;
            List<Long> noReadingNewsIds = newsMapper.findNoReadingNewsId(type, login);
            if(CollectionUtil.isNotEmpty(noReadingNewsIds)){
                unReadMsg=noReadingNewsIds.size();
            }
           // 用户上线提醒
            sendMessage("用户"+login+"已上线("+("1".equals(login) ? "求职者)":"招聘者)")+",未读消息数:"+unReadMsg,session);
            log.info("有新窗口开始监听用户详情id:" + login +",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        clients.remove(login);
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("释放的login为："+login);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + login + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message,session);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message,Session session) throws IOException {
        if(session != null){
            session.getBasicRemote().sendText(message);
        }
    }
    /**
     * 校验是否在线,在线需要返回用户session信息
     */
    public Session checkIsOnline(String login) throws IOException {
        for (String onLineLogin : clients.keySet()) {
            if(login.equals(onLineLogin)){
                return clients.get(login);
            }
        }
        return null;
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}