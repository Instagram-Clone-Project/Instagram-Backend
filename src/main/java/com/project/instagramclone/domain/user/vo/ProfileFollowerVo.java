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

    @Builder
    private ProfileFollowerVo(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
