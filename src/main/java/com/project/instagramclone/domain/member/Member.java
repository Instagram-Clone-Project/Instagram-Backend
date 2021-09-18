package com.project.instagramclone.domain.member;

import com.project.instagramclone.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    //아래 필드들은 필수X
    //나중에 수정 예정
    private String phoneNumber;

    private String webSite;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String profileImgUrl;
}
