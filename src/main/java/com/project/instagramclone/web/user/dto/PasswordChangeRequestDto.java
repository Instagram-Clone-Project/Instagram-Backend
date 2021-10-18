package com.project.instagramclone.web.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "비밀번호 변경 요청 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordChangeRequestDto {

    @NotBlank(message = "이전 비밀번호를 입력해주세요.")
    @ApiModelProperty(example = "이전 비밀번호")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호를 입력해주세요.")
    @ApiModelProperty(example = "새 비밀번호")
    private String newPassword;

    @NotBlank(message = "새 비밀번호를를 확인해주세요.")
    @ApiModelProperty(example = "새 비밀번호 확인")
    private String confirmPassword;
}
