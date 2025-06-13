package com.example.chatservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// STOMP 기능 활성화
@EnableWebSocketMessageBroker
@Configuration
public class StompConfiguration implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
//    커넥션 경로 설정
    registry.addEndpoint("/stomp/chats");
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 메시지 브로커를 구성하는 메서드입니다.
    // 클라이언트가 메시지를 발행(publish)할 때 사용할 경로와,
    // 클라이언트가 서버로부터 메시지를 구독(subscribe)할 경로를 설정합니다.
    registry.setApplicationDestinationPrefixes("/pub");
    registry.enableSimpleBroker("/sub");
  }
}
