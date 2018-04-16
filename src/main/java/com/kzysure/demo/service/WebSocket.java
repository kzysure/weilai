package com.kzysure.demo.service;


import com.kzysure.demo.VO.WebsocketVO;
import com.kzysure.demo.utils.ServerEncoder;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:kzysure@kzysure.com">kzysure</a>
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@ServerEndpoint(value = "/websocket",encoders = {ServerEncoder.class})
@Slf4j
public class WebSocket {
  private Session session;
  private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
  @OnOpen
  public void onOpen(Session session){
    this.session = session;
    webSockets.add(this);
    log.info("【websocket消息】有新的连接，总数：{}",webSockets.size());
  }
  @OnClose
  public void onClose(){
    webSockets.remove(this);
    log.info("【websocket消息】关闭连接");

  }
  @OnMessage
  public void onMessage(String message){
    log.info("收到客户端发来的消息：{}",message);
  }
  public void sendMessage(WebsocketVO websocketVO){
    for (WebSocket webSocket: webSockets){
      log.info("【Websocket】，广播消息：{}",websocketVO);
      try{
        webSocket.session.getBasicRemote().sendObject(websocketVO);

      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

}
