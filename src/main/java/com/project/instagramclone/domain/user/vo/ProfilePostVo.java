package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.post.entity.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfilePostVo {

    @ApiModelProperty(example = "게시물 ID")
    private Long postId;

    @ApiModelProperty(example = "내용")
    private String content;

    @ApiModelProperty(example = "게시물 좋아요 수")
    private Long likeCount;

    @ApiModelProperty(example = "게시물 댓글 수")
    private Long commentCount;

    @ApiModelProperty(example = "게시물 사진")
    private List<PostImageVo> images;

    @ApiModelProperty(example = "게시물 작성 날짜")
    private LocalDateTime createdDate;

    @Builder
    private ProfilePostVo(Post post, Long likeCount, Long commentCount, List<PostImageVo> images) {
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.images = images;
        this.createdDate = post.getCreatedDate();
    }
}
