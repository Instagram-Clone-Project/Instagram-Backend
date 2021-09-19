package com.project.instagramclone.web.post.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostSaveDto {

    private String content;
//    private User user;
    private List<MultipartFile> files;

}
