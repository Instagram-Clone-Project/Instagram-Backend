package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.vo.ProfileFollowerVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "프로필 팔로워 목록 응답 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowersResponseDto {

    @ApiModelProperty(example = "팔로워 정보")
    List<ProfileFollowerVo> followers;

    @Builder
    private FollowersResponseDto(List<ProfileFollowerVo> followers) {
        this.followers = followers;
    }
}
