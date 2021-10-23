package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomePostVo {

    @ApiModelProperty(position = 1, value = "작성자 ID")
    private String username;

    @ApiModelProperty(position = 2, value = "작성자 프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty( value = "게시물 ID")
    private Long postId;

    @ApiModelProperty( value = "게시물 사진 목록")
    private List<PostImageVo> images;

    @ApiModelProperty( value = "게시물 내용")
    private String content;

    @ApiModelProperty( value = "게시물 좋아요 수")
    private Long likeCount;

    @ApiModelProperty( value = "게시물 댓글 수")
    private Long commentCount;

    @ApiModelProperty( value = "게시물 댓글 목록")
    private List<CommentVo> comments;

    @ApiModelProperty( value = "게시물 작성날짜")
    private LocalDateTime createdDate;

    @Builder
    private HomePostVo(User user, Post post, List<PostImageVo> images, Long likeCount,
                       Long commentCount, List<CommentVo> comments) {
        this.username = user.getUsername();
        this.profileImageUrl = user.getProfileImageUrl();
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.images = images;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.comments = comments;
    }
}
