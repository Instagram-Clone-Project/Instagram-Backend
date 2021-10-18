package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.vo.ProfileFollowerVo;
import com.project.instagramclone.domain.user.vo.ProfileFollowingVo;
import com.project.instagramclone.domain.user.vo.ProfilePostVo;
import com.project.instagramclone.domain.user.vo.ProfileUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "프로필 요청 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponseDto {

    @ApiModelProperty(example = "사용자 정보")
    private ProfileUserVo user;

    @ApiModelProperty(example = "게시물 수")
    private Long postCount;

    @ApiModelProperty(example = "팔로워 수")
    private Long followerCount;

    @ApiModelProperty(example = "팔로잉 수")
    private Long followingCount;

    @ApiModelProperty(example = "게시물 정보")
    private List<ProfilePostVo> posts;

    @Builder
    private ProfileResponseDto(ProfileUserVo user, Long postCount, Long followerCount, Long followingCount, List<ProfilePostVo> posts) {
        this.user = user;
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.posts = posts;
    }
}
