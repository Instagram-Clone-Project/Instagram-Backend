package com.project.instagramclone.web.post.controller;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.service.PostService;
import com.project.instagramclone.service.S3UploadService;
import com.project.instagramclone.web.post.dto.PostSaveDto;
import com.project.instagramclone.web.post.dto.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final S3UploadService s3UploadService;

    @PostMapping("/api/post")
    public void postSave(@ModelAttribute PostSaveDto postSaveDto) throws IOException {
        Post post = new Post();
        post.setContent(postSaveDto.getContent());
        //        post.setUser(postSaveDto.getUser());
        post.setCreatedDate(LocalDateTime.now());
        post.setModifiedDate(LocalDateTime.now());

        postService.postSave(post);

        List<MultipartFile> files = postSaveDto.getFiles();
        for (MultipartFile file : files) {
            s3UploadService.uploadSave(file, "static", post);
        }
    }

    @PutMapping("/api/post/{post_id}")
    public void postUpdate(@RequestBody PostUpdateDto postUpdateDto, @PathVariable("post_id") Long postId) {
        postService.updatePost(postId, postUpdateDto.getContent());

    }

}
