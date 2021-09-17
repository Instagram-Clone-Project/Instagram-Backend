package com.project.instagramclone.web.user.dto;

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
    private String memberName;
    private String id;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .memberName(memberName)
                .id(id)
                .password(password)
                .role(MemberRole.USER)
                .build();
    }
}
