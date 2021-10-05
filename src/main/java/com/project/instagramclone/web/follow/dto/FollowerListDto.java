package com.project.instagramclone.web.follow.dto;

import lombok.Data;

@Data
public class FollowerListDto {

    private String profileImageUrl;
    private String username;
    private boolean followRelation; // 맞팔인지 여부 확인
}
