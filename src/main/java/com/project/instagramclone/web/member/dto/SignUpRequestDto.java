package com.project.instagramclone.web.member.dto;

import com.project.instagramclone.domain.member.Member;
import com.project.instagramclone.domain.member.MemberRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String name;
    private String id;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .id(id)
                .password(password)
                .role(MemberRole.USER)
                .build();
    }
}
