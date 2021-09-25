package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String name;
    private String username;
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
