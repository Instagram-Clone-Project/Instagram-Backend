package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "로그인 응답 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    @ApiModelProperty(example = "사용자 이름")
    private String username;

    @ApiModelProperty(example = "프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(example = "접근 토큰 (JWT)")
    private String accessToken;

    //나중에 팔로워, 팔로잉 목록 추가
}
