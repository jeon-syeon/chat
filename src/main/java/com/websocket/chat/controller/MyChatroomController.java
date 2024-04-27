package com.websocket.chat.controller;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/my_chatrooms")
@RequiredArgsConstructor
public class MyChatroomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping
    public List<ChatRoom> getMyChatRooms(@RequestParam String username) {
        return chatRoomRepository.findmyroom(username);
    }

}