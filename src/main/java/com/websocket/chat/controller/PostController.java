package com.websocket.chat.controller;

import com.websocket.chat.model.Post;
import com.websocket.chat.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.findAllPosts();
    }

    @PostMapping
    public Post creatPost(@RequestBody Post post){
        return postService.createPost(post.getTitle(), post.getContent(), post.getAuthor());
    }
}
