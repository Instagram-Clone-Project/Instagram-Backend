package com.project.instagramclone.web.follow.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class FollowingListDto {

    private String profileImageUrl;
    private String username;
    private boolean followRelation; // 맞팔인지 여부 확인
    private boolean loginUser;      // 리스트를 보는게 본인 계정인 경우
}
