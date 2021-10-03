package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.Gender;
import com.project.instagramclone.domain.user.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String name;
    private String username;
    private String website;
    private String description;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public User toEntity() {
        return User.builder()
                .name(name)
                .username(username)
                .webSite(website)
                .description(description)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .build();
    }
}
