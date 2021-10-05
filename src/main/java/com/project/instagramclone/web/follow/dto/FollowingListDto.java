package com.project.instagramclone.web.follow.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class FollowingListDto {

    private String profileImageUrl;
    private String username;
    private boolean followRelation; // 맞팔인지 여부 확인

}
