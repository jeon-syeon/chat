package com.websocket.chat.controller;

import com.websocket.chat.model.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/chat")
public class UserInfoController {
    @PostMapping("/name")
    public ResponseEntity<String> setUsername(@RequestBody UserInfo user, HttpSession session) {
        // 사용자 이름을 세션에 저장
        session.setAttribute("username", user.getUsername());
        // 성공 메시지 반환
        return ResponseEntity.ok("Username saved successfully.");
    }
}
