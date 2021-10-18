package com.project.instagramclone.web.like.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(value = "좋아요 정보")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {

    private Long postId;
    private Long totalLike;


}
