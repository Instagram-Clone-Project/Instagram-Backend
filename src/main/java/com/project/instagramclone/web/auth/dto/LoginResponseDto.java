package com.project.instagramclone.web.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "로그인 응답 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    @ApiModelProperty(example = "사용자 이름 (ID)")
    private String username;

    @ApiModelProperty(example = "성명")
    private String name;

    @ApiModelProperty(example = "프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(example = "접근 토큰 (JWT)")
    private String accessToken;

    @Builder
    private LoginResponseDto(String username, String name, String profileImageUrl, String accessToken) {
        this.username = username;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.accessToken = accessToken;
    }
}
