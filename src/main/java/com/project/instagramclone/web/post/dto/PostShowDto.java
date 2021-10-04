package com.project.instagramclone.web.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PostShowDto {

    @ApiModelProperty(example = "게시글ID")
    private Long PostId;

    @ApiModelProperty(example = "유저ID")
    private String username;

    @ApiModelProperty(example = "유저이름")
    private String name;

    @ApiModelProperty(example = "프로필이미지")
    private String profileUrl;

    @ApiModelProperty(example = "[게시글 이미지 url1,게시글 이미지 url1,게시글 이미지 url1, 게시글 이미지 url1]")
    private List<String> photoList = new ArrayList<>();

    @ApiModelProperty(example = "게시글")
    private String content;

//    private List<favoriteDto> favoriteList = new ArrayList<>();

    @ApiModelProperty(example = "작성일")
    private LocalDateTime createdDate;

    @ApiModelProperty(example = "수정일")
    private LocalDateTime modifiedDate;

    private List<CommentDto> commentList = new ArrayList<>();

}
