package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileFollowerVo {

    @ApiModelProperty(example = "팔로워 ID")
    private String username;

    @ApiModelProperty(example = "팔로워 성명")
    private String name;

    @ApiModelProperty(example = "팔로워 프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(notes = "팔로우 여부 (팔로우O: true, 팔로우X: false)")
    private boolean followRelation;

    @Builder
    private ProfileFollowerVo(User user, boolean followRelation) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followRelation = followRelation;
    }
}
