package com.websocket.chat.repo;

import com.websocket.chat.model.ChatRoom;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public void createChatRoom(ChatRoom chatroom) {
        String roomName = chatroom.getTitle()+ " : " + chatroom.getSender() + "와(과) " + chatroom.getRecipient() + "의 채팅";
        chatRoomMap.put(chatroom.getRoomId(), chatroom);
    }

    public List<ChatRoom> findmyroom(String username) {
        List<ChatRoom> myChatRooms = new ArrayList<>();
        // 채팅방 생성순서 최근 순으로 반환
        for (ChatRoom chatRoom : chatRoomMap.values()) {
            if (chatRoom.getSender().equals(username) || chatRoom.getRecipient().equals(username)) {
                myChatRooms.add(chatRoom);
                System.out.println(chatRoom.getTitle());
            }
        }
        System.out.println("aaaaa");
        Collections.reverse(myChatRooms);
        return myChatRooms;
    }


}
