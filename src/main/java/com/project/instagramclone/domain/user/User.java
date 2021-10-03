package com.project.instagramclone.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.web.user.dto.UserRequestDto;
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

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;
    //사용자 이름
    @Column(nullable = false)
    private String name;
    //사용자 아이디
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    //아래 필드들은 필수X
    //나중에 수정 예정
    private String webSite;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String profileImageUrl;

    @JsonIgnore
    private String verificationCode;

    //이메일 인증 활성화 여부
    @JsonIgnore
    private boolean enabled;

    public void createVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void changeEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void updateUser(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.username = userRequestDto.getUsername();
        this.webSite = userRequestDto.getWebsite();
        this.description = userRequestDto.getDescription();
        this.email = userRequestDto.getEmail();
        this.phoneNumber = userRequestDto.getPhoneNumber();
        this.gender = userRequestDto.getGender();
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    // 수혁

//    @OneToMany(mappedBy = "follower")
//    private List<Follow> follwer;
//
//    @OneToMany(mappedBy = "following")
//    private List<Follow> follwing;

}
