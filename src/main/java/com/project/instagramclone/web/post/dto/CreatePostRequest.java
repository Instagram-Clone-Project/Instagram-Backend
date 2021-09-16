package com.project.instagramclone.web.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePostRequest {

    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
