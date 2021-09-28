package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    //@ApiModelProperty(example = "전화번호")
    //private String phoneNumber;

    @ApiModelProperty(example = "이메일 주소")
    private String email;

    @ApiModelProperty(example = "성명")
    private String name;

    @ApiModelProperty(example = "사용자 이름")
    private String username;

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
