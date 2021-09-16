package com.project.instagramclone.web.post.controller;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.service.PostService;
import com.project.instagramclone.web.post.dto.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        Post post = new Post();
        post.setContent(createPostRequest.getContent());
        post.setCreatedDate(LocalDateTime.now());
        post.setModifiedDate(LocalDateTime.now());

        postService.createPost(post);
    }

}
