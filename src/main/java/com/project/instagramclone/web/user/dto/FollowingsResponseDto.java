package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.vo.ProfileFollowingVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "프로필 팔로잉 목록 응답 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingsResponseDto {

    @ApiModelProperty(example = "팔로잉 정보")
    List<ProfileFollowingVo> followings;

    @Builder
    private FollowingsResponseDto(List<ProfileFollowingVo> followings) {
        this.followings = followings;
    }
}
