package com.project.instagramclone.web.like.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "좋아요 응답 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseDto {

    @ApiModelProperty(value = "좋아요 대상(게시물|댓글|답글) ID")
    private Long id;

    @ApiModelProperty(value = "좋아요 수")
    private Long totalLike;

    @Builder
    private LikeResponseDto(Long id, Long totalLike) {
        this.id = id;
        this.totalLike = totalLike;
    }
}
