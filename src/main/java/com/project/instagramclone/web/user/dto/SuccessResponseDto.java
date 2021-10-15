package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "기본 응답 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuccessResponseDto {

    @ApiModelProperty(example = "기본 응답 메세지")
    private String message;

    @Builder
    private SuccessResponseDto(String message) {
        this.message = message;
    }
}
