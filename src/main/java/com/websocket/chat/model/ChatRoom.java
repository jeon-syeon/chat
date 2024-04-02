package com.websocket.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private String sender;
    private String recipient;
    private String title;

    public String getTitle(){
        return title;
    }

    public String getSender(){
        return sender;
    }
    public String getName(){
        return recipient;
    }
    public static ChatRoom create(String name, String s, String r) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();

        chatRoom.name = name;
        chatRoom.sender = s;
        chatRoom.recipient = r;
        System.out.println("chatroom:"+chatRoom.name+chatRoom.sender+chatRoom.sender);
        return chatRoom;
    }
}
