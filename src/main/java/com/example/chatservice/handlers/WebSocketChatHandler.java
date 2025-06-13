package com.example.chatservice.handlers;

// WebSocketHandler 를 상속 시 5개의 메서드를 구현해야함.
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
// TextWebSocketHandler 를 포함 시, 원하는 메서드만 구현 가능.
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

//  어떠한 웹소켓 클라이언트가 접속했는지 정보를 담기 위한 Map
  final Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    //    웹소켓 클라이언트가 처음 서버로 연결한 이후, 실행되는 로직 담당
    log.info("{} connected", session.getId());
    webSocketSessionMap.put(session.getId(), session);
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
      throws Exception {
    //    서버로 연결된 웹소켓 클라이언트에서 메시지가 왔을 때, 실행되는 로직 담당
    log.info("{} sent {}", session.getId(), message.getPayload());

    webSocketSessionMap.values().forEach(
        webSocketSession -> {
          try {
            webSocketSession.sendMessage(message);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    //    서버로 연결된 웹소켓 클라이언트가 연결을 끊었을 때, 실행되는 로직 담당
    log.info("{} disconnected", session.getId());
    webSocketSessionMap.remove(session.getId());
  }
}
