package com.project.instagramclone.web.activity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ActivityListDto {

    private AlarmType alarmType;
    private String userName;
    private String profileImgUrl;
    private String postImgUrl;
    private LocalDateTime createdTime;
        public ActivityListDto(String userName, String profileImgUrl, String postImgUrl){
        this.userName = userName;
        this.postImgUrl = postImgUrl;
        this.profileImgUrl = profileImgUrl;
    }
}
