package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.Gender;
import com.project.instagramclone.domain.user.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditResponseDto {

    private String profileImageUrl;
    private String name;
    private String username;
    private String website;
    private String description;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public EditResponseDto(User user) {
        this.profileImageUrl = user.getProfileImageUrl();
        this.name = user.getName();
        this.username = user.getUsername();
        this.website = user.getWebSite();
        this.description = user.getDescription();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.gender = user.getGender();
    }
}
