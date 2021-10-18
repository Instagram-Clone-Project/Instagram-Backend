package com.project.instagramclone.web.user.dto;

import com.project.instagramclone.domain.user.Gender;
import com.project.instagramclone.domain.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "프로필 편집 요청 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditRequestDto {

    @ApiModelProperty(example = "이름")
    private String name;

    @ApiModelProperty(example = "사용자 이름 (ID)")
    private String username;

    @ApiModelProperty(example = "웹사이트")
    private String website;

    @ApiModelProperty(example = "설명")
    private String description;

    @ApiModelProperty(example = "이메일")
    private String email;

    @ApiModelProperty(example = "전화번호")
    private String phoneNumber;

    @ApiModelProperty(example = "성별")
    private Gender gender;
}
