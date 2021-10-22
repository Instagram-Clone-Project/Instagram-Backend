package com.project.instagramclone.domain.user.vo;

import com.project.instagramclone.domain.comment.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentVo {

    @ApiModelProperty(value = "댓글 ID")
    private Long commentId;

    @ApiModelProperty(value = "댓글 작성자 ID")
    private String username;

    @ApiModelProperty(value = "댓글 작성자 프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(value = "댓글 내용")
    private String content;

    @ApiModelProperty(value = "댓글 좋아요 수")
    private Long likeCount;

    @ApiModelProperty(value = "답글 목록")
    List<ReplyVo> replies;

    @ApiModelProperty(value = "답글 수")
    private Long replyCount;

    @ApiModelProperty(value = "댓글 작성날짜")
    private LocalDateTime createdDate;

    @Builder
    private CommentVo(Comment comment, Long likeCount,
                      List<ReplyVo> replies, Long replyCount) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();
        this.username = comment.getUser().getUsername();
        this.profileImageUrl = comment.getUser().getProfileImageUrl();
        this.likeCount = likeCount;
        this.replies = replies;
        this.replyCount = replyCount;
    }
}
