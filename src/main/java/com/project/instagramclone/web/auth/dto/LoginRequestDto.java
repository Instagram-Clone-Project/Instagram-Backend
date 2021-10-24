package com.project.instagramclone.web.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "로그인 요청 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    @ApiModelProperty(example = "사용자 이름")
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String username;

    @ApiModelProperty(example = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
