package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUserVo {

    @ApiModelProperty(example = "성명")
    private String name;

    @ApiModelProperty(example = "사용자 이름 (ID)")
    private String username;

    @ApiModelProperty(example = "프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(example = "웹사이트")
    private String website;

    @ApiModelProperty(example = "소개")
    private String introduction;

    @Builder
    private ProfileUserVo(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
        this.website = user.getWebSite();
        this.introduction = user.getDescription();
    }
}
