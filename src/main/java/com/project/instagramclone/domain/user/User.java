package com.project.instagramclone.domain.user;

import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.follow.Follow;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;
    //사용자 이름
    @Column(nullable = false)
    private String name;
    //사용자 아이디
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    //아래 필드들은 필수X
    //나중에 수정 예정
    private String phoneNumber;

    private String webSite;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String profileImgUrl;


    // 수혁

    @OneToMany(mappedBy = "follower")
    private List<Follow> follwer;

    @OneToMany(mappedBy = "following")
    private List<Follow> follwing;

}
