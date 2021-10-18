package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.Gender;
import com.project.instagramclone.domain.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "프로필 편집 전 기본 응답 정보", description = "프로필 편집 화면에 보여줄 회원 기존 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditResponseDto {

    @ApiModelProperty(example = "기존 프로필 사진")
    private String profileImageUrl;

    @ApiModelProperty(example = "기존 성명")
    private String name;

    @ApiModelProperty(example = "기존 사용자 이름")
    private String username;

    @ApiModelProperty(example = "기존 웹사이트")
    private String website;

    @ApiModelProperty(example = "기존 설명")
    private String description;

    @ApiModelProperty(example = "기존 이메일")
    private String email;

    @ApiModelProperty(example = "기존 전화번호")
    private String phoneNumber;

    @ApiModelProperty(example = "기존 성별")
    private Gender gender;

    @Builder
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
