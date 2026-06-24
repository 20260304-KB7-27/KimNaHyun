package org.scoula.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app") // ws://localhost:8080/chat-app
                .setAllowedOrigins("*");  // CORS 허용
    }

    //
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){

        // /topic 시작하는 경로는 브로커가 처리 ( 서버 -> 클라이언트 )
        // /topic/chat, /topic/greeting
        registry.enableSimpleBroker("/topic");

        // (클라이언트 -> 서버)
        // /app/chat : 사용자가 message를 보내면 서버에서 @MessageMapping("/chat") 으로 연결
        registry.setApplicationDestinationPrefixes("/app");

    }
}
