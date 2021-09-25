package com.project.instagramclone.web.post.dto;

import com.project.instagramclone.domain.post.entity.Photo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PostShowDto {

    private String content;
    private List<Photo> photoList;

}
