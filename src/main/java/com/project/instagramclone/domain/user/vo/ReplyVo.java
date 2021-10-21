package com.project.instagramclone.domain.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyVo {

    @ApiModelProperty(value = "답글 ID")
    private Long replyId;

    @ApiModelProperty(value = "답글 작성자 ID")
    private String username;

    @ApiModelProperty(value = "답글 작성자 프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(value = "답글 내용")
    private String content;

    @ApiModelProperty(value = "답글 좋아요 수")
    private Long likeCount;

    @ApiModelProperty(value = "답글 작성날짜")
    private LocalDateTime createdDate;

    @Builder
    private ReplyVo(Long replyId, String username, String profileImageUrl, String content,
                    Long likeCount, LocalDateTime createdDate) {
        this.replyId = replyId;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.content = content;
        this.likeCount = likeCount;
        this.createdDate = createdDate;
    }
}
