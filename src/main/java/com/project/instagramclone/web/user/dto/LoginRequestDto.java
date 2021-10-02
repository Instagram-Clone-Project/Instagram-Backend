package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "로그인 요청 정보")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @ApiModelProperty(example = "사용자 이름")
    private String username;

    @ApiModelProperty(example = "비밀번호")
    private String password;
}
