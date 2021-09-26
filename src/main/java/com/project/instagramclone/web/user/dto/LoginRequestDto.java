package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
