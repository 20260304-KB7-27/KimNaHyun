package org.scoula.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate; // 🌟 인터페이스 대신 구체 클래스 임포트
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 클라이언트가 /pub/chat/{roomId} 주소로 메시지를 발행(Publish)하면 호출됩니다.
     */
    @MessageMapping("/chat/{roomId}")
    public void chat(@DestinationVariable("roomId") String roomId, ChatMessage message) {
        log.info("수신된 메시지 - 방번호: {}, 송신자: {}, 타입: {}", roomId, message.getName(), message.getType());

        if ("ENTER".equals(message.getType())) {
            message.setContent(message.getName() + "님이 대화방에 입장하셨습니다.");
        }
        else if ("QUIT".equals(message.getType())) {
            message.setContent(message.getName() + "님이 대화방에서 퇴장하셨습니다.");
        }

        // 특정 방 번호 주소로 브로드캐스팅
        messagingTemplate.convertAndSend("/topic/chat/" + roomId, message);
    }

    @GetMapping("/chat")
    public String chatView() {
        return "chat"; // WEB-INF/views/chat.jsp 뷰 파일 리턴
    }
}