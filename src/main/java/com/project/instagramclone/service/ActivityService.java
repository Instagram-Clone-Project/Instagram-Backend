package com.project.instagramclone.service;

import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.likes.Likes;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.web.activity.dto.ActivityDto;
import com.project.instagramclone.web.activity.dto.ActivityListDto;
import com.project.instagramclone.web.activity.dto.AlarmType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final LikeRepository likeRepository;

    public ActivityDto getAlarmList(User user) {
        List<Likes> postLike = likeRepository.getPostLike(user.getUserId());
        List<Likes> commentLike = likeRepository.getCommentLike(user.getUserId());
        ActivityDto activityDto = new ActivityDto();

        
        // 게시글 좋아요 알람
        for(Likes likes : postLike){
            // 자기 자신 좋아요 알람은 스킵
            if(likes.getUser().getUserId() == user.getUserId()) continue;

            ActivityListDto activityListDto = new ActivityListDto();
            setPostValue(likes, activityListDto,activityDto);
        }

        // 댓글 좋아요 알람
        for(Likes likes : commentLike){
            // 자기 자신 좋아요 알람은 스킵
            if(likes.getUser().getUserId() == user.getUserId()) continue;
            ActivityListDto activityListDto = new ActivityListDto();
            setCommentValue(activityDto, likes, activityListDto);
        }

        return activityDto;
    }

    private void setCommentValue(ActivityDto activityDto, Likes likes, ActivityListDto activityListDto) {
        activityListDto.setAlarmType(AlarmType.COMMENT);
        activityListDto.setUserName(likes.getUser().getUsername());
        activityListDto.setProfileImgUrl(likes.getUser().getProfileImageUrl());
        activityListDto.setCreatedTime(likes.getCreatedDate());
        activityDto.getActivityListDtos().add(activityListDto);
    }

    private void setPostValue(Likes likes, ActivityListDto activityListDto, ActivityDto activityDto) {
        activityListDto.setAlarmType(AlarmType.POST);
        activityListDto.setPostImgUrl(likes.getPost().getPhotos().get(0).getRoute());   // 대표사진 한개만
        activityListDto.setUserName(likes.getUser().getUsername());
        activityListDto.setProfileImgUrl(likes.getUser().getProfileImageUrl());
        activityListDto.setCreatedTime(likes.getCreatedDate());
        activityDto.getActivityListDtos().add(activityListDto);
    }
}
