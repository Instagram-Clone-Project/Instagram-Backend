package com.project.instagramclone.web.post.dto;

import com.project.instagramclone.domain.photo.entity.Photo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostTestDto {

    private String content;
    private List<Photo> photoList = new ArrayList<>();

    public PostTestDto(String content, List<Photo> photoList) {
        this.content = content;
        this.photoList = photoList;
    }
}
