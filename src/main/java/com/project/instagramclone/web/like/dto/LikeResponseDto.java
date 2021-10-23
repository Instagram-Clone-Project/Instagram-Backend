package com.project.instagramclone.web.like.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "좋아요 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeResponseDto {

    private Long id;
    private Long totalLike;

    @Builder
    private LikeResponseDto(Long id, Long totalLike) {
        this.id = id;
        this.totalLike = totalLike;
    }
}
