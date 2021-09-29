package com.project.instagramclone.web.post.dto;

import com.project.instagramclone.domain.post.entity.Photo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostShowDto {

    @ApiModelProperty(example = "게시글")
    private String content;

    @ApiModelProperty(example = "게시글 사진 url")
    private List<String> photoList = new ArrayList<>();

    private List<CommentDto> commentList = new ArrayList<>();

}
