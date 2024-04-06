package com.websocket.chat.controller;

import com.websocket.chat.model.ChatRoom;
import com.websocket.chat.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    // 게시글 작성자와 채팅을 원하는 사람 간의 1:1 채팅방 생성 요청
    @PostMapping("/room/create")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoom chatRoom) {
        // 채팅방 생성 로직
        chatRoomRepository.createChatRoom(chatRoom);
        // 채팅방 생성 후 생성된 채팅방 정보를 클라이언트에 반환
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }



}
