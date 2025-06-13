package com.example.chatservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class StompChatController {

  @MessageMapping("/chats") // 클라이언트가 "/pub/chats" 경로로 메시지를 보내면 이 메서드가 호출됩니다.
  @SendTo("/sub/chats")     // 이 메서드에서 반환한 메시지를 "/sub/chats"를 구독 중인 모든 클라이언트에게 전달합니다.
  public String handleMessage(@Payload String message) {
    log.info("{} received", message);

    return message;
  }

}
