package com.project.instagramclone.web.follow.dto;

import com.project.instagramclone.domain.follow.Follow;
import com.project.instagramclone.domain.user.User;
import lombok.Data;

@Data
public class FollowSaveDto {

    private User fromUser;
    private User toUser;

    public Follow toEntity(){
        return Follow.builder()
                .following(fromUser)
                .follower(toUser)
                .build();
    }
}
