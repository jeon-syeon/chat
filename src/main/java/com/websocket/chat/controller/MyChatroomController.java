package com.websocket.chat.controller;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/my_chatrooms")
@RequiredArgsConstructor
public class MyChatroomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping
    public List<ChatRoom> getMyChatRooms() {
        String currentUsername = getCurrentUsername();
        return chatRoomRepository.findChatRoomsByUser(currentUsername);
    }

    // 실제로는 현재 사용자의 이름을 어떻게 가져올지에 대한 로직이 들어갈 것입니다.
    private String getCurrentUsername() {
        // 예시로 고정된 사용자 이름을 반환하는 메소드
        return "username123";
    }
}