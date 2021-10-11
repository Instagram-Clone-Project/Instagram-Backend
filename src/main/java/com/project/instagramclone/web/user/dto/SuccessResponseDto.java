package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "기본 응답 정보")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDto {

    @ApiModelProperty(example = "기본 응답 메세지")
    private String message;
}
