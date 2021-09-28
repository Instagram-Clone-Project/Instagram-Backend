package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyAccountRequestDto {

    @ApiModelProperty(example = "이메일 주소")
    private String email;

    @ApiModelProperty(example = "인증번호")
    private String authCode;
}
