package org.scoula.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {

    // 임시로 메모리에 저장하는 채팅방 목록 (Key: 방ID, Value: 방이름)
    private final Map<String, String> rooms = new LinkedHashMap<>();

    // 1. 대화방 개설 기능
    @PostMapping("/room")
    public Map<String, String> createRoom(@RequestParam("name") String name) {
        String roomId = UUID.randomUUID().toString(); // 랜덤한 고유 방 ID 생성
        rooms.put(roomId, name);

        Map<String, String> response = new HashMap<>();
        response.put("roomId", roomId);
        response.put("roomName", name);
        return response;
    }

    // 2. 대화방 선택을 위한 목록 조회 기능
    @GetMapping("/rooms")
    public List<Map<String, String>> roomList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (String id : rooms.keySet()) {
            Map<String, String> map = new HashMap<>();
            map.put("roomId", id);
            map.put("roomName", rooms.get(id));
            list.add(map);
        }
        return list;
    }
}