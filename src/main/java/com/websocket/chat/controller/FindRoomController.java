package com.websocket.chat.controller;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindRoomController {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/api/my_chatrooms")
    public List<ChatRoom> getMyChatRooms(@RequestParam String username) {
        return chatRoomRepository.findmyroom(username);
    }

}
