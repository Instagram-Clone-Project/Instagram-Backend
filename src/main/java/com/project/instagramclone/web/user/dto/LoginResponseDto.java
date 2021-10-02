package com.project.instagramclone.web.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String username;
    private String profileImageUrl;
    private String accessToken;

    //나중에 팔로워, 팔로잉 목록 추가
}
