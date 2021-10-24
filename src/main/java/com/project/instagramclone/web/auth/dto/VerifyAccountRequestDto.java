package com.project.instagramclone.web.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "계정 활성화 요청 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyAccountRequestDto {

    @ApiModelProperty(example = "이메일 주소")
    private String email;

    @ApiModelProperty(example = "인증번호")
    private String authCode;
}
