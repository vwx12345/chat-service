package com.example.chatservice.handlers;

// WebSocketHandler 를 상속 시 5개의 메서드를 구현해야함.
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

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    //    웹소켓 클라이언트가 처음 서버로 연결한 이후, 실행되는 로직 담당
    log.info("{} connected", session.getId());
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
      throws Exception {
    //    서버로 연결된 웹소켓 클라이언트에서 메시지가 왔을 때, 실행되는 로직 담당
    super.handleMessage(session, message);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    //    서버로 연결된 웹소켓 클라이언트가 연결을 끊었을 때, 실행되는 로직 담당
    log.info("{} disconnected", session.getId());
  }
}
