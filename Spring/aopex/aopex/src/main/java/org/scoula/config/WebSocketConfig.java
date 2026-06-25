package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor; // 🌟 추가

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app")
                .setAllowedOriginPatterns("*") // 모든 CORS 허용 패턴 유지
                .addInterceptors(new HttpSessionHandshakeInterceptor()) // 🌟 세션 핸드셰이크 인터셉터 추가 (연결 유연성 확보)
                .withSockJS(); // 🌟 핵심: SockJS Fallback 기능 활성화 (연결 실패 차단)
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){

        // /topic 시작하는 경로는 브로커가 처리 ( 서버 -> 클라이언트 )
        registry.enableSimpleBroker("/topic");

        // (클라이언트 -> 서버)
        registry.setApplicationDestinationPrefixes("/app");
    }
}