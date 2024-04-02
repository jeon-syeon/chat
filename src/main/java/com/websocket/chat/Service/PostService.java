package com.websocket.chat.Service;

import com.websocket.chat.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();

    public List<Post> findAllPosts() {
        return posts;
    }

    public Post createPost(String title, String content, String author) {
        Post post = new Post(title, content, author);
        posts.add(post);
        return post;
    }
}
