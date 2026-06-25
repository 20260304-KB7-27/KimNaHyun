package org.scoula.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    // 메시지 타입 정의 (입장, 대화, 나가기)
    public enum MessageType {
        ENTER, TALK, QUIT
    }

    private MessageType type; // 🌟 추가: ENTER, TALK, QUIT 구분
    private String roomId;    // 🌟 추가: 메시지가 속한 특정 방 ID
    private String name;      // 보낸 사람 이름
    private String content;   // 채팅 내용
}