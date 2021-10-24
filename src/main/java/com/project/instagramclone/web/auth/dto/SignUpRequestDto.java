package com.project.instagramclone.web.auth.dto;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "회원가입 요청 정보")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {

    //@ApiModelProperty(example = "전화번호")
    //private String phoneNumber;

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @ApiModelProperty(example = "이메일 주소")
    private String email;

    @NotBlank(message = "성명을 입력해주세요.")
    @ApiModelProperty(example = "성명")
    private String name;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    @ApiModelProperty(example = "사용자 이름")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @ApiModelProperty(example = "비밀번호")
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .username(username)
                .password(password)
                .role(UserRole.USER)
                .build();
    }
}
