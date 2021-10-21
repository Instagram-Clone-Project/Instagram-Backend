package com.project.instagramclone.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.web.user.dto.EditRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "posts")
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


    @OneToMany(mappedBy = "user")
    List<Post> posts = new ArrayList<>();

    public void changeVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void changeEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void updateUser(EditRequestDto editRequestDto) {
        this.name = editRequestDto.getName();
        this.username = editRequestDto.getUsername();
        this.webSite = editRequestDto.getWebsite();
        this.description = editRequestDto.getDescription();
        this.email = editRequestDto.getEmail();
        this.phoneNumber = editRequestDto.getPhoneNumber();
        this.gender = editRequestDto.getGender();
    }

    public void updateProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
