package com.project.instagramclone.service;

import com.project.instagramclone.domain.follow.Follow;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.domain.follow.FollowRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.Validation;
import com.project.instagramclone.web.follow.dto.FollowCntDto;
import com.project.instagramclone.web.follow.dto.FollowSaveDto;
import com.project.instagramclone.web.follow.dto.FollowerListDto;
import com.project.instagramclone.web.follow.dto.FollowingListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowQueryRepository followQueryRepository;

    @Transactional
    public void save(User fromUser, Long toUserId){
        User toUser = userRepository.findById(toUserId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        FollowSaveDto followSaveDto = new FollowSaveDto();
        followSaveDto.setFromUser(fromUser);
        followSaveDto.setToUser(toUser);

        Follow follow = followSaveDto.toEntity();

        followRepository.save(followSaveDto.toEntity());
    }

    @Transactional
    public void delete(User fromUser, Long toUserId){
        User toUser = userRepository.findById(toUserId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        Follow follow = followQueryRepository.findFollowTableId(fromUser.getUserId(), toUserId)
                .orElseThrow(()-> new CustomException(ErrorCode.FOLLOW_NOT_FOUND));

        followRepository.delete(follow);
    }

    @Transactional
    public FollowCntDto getFollowInfo(Long userId) {
        Long follower = followQueryRepository.getFollower(userId);
        Long following = followQueryRepository.getFollowing(userId);

        FollowCntDto followCntDto = new FollowCntDto();
        followCntDto.setFollower(follower);
        followCntDto.setFollowing(following);

        return followCntDto;
    }

    @Transactional
    public List<FollowerListDto> getFollowerList(Long userId, User loginUser) {

        List<Follow> followList = followQueryRepository.findFollowerList(userId);

        List<FollowerListDto> followerListDtos = new ArrayList<>();
        for(Follow f : followList){
            if(f.getFollowing().getUserId() == loginUser.getUserId()) continue; // 자기 자신 스킵

            FollowerListDto tmp = new FollowerListDto();
            User user = userRepository.findById(f.getFollowing().getUserId()).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

            tmp.setUsername(user.getUsername());
            tmp.setProfileImageUrl(user.getProfileImageUrl());
            Optional<Follow> relation = followQueryRepository.findRelation(loginUser.getUserId(), f.getFollowing().getUserId());


            if(userId == loginUser.getUserId()){
                tmp.setLoginUser(true);
            }

            if(relation.isPresent()){
                tmp.setFollowRelation(true);
            }
            else{
                tmp.setFollowRelation(false);
            }
            followerListDtos.add(tmp);
        }

        return followerListDtos;
    }

    @Transactional
    public List<FollowingListDto> getFollowingList(Long userId, User loginUser){
        List<Follow> followingList = followQueryRepository.findFollowingList(userId);
        List<FollowingListDto> followingListDtos = new ArrayList<>();

        // 지금 현재 테이블에는 userId를 팔로잉 하는 테이블 리스트의 정보가 나온다.
        // 그렇다면 Follow (Following = userId Follower = 다른사람)

        for(Follow f : followingList){
            if(f.getFollower().getUserId() == loginUser.getUserId()) continue; // 자기 자신 스킵

            FollowingListDto tmp = new FollowingListDto();

            User user = userRepository.findById(f.getFollower().getUserId()).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

            tmp.setUsername(user.getUsername());
            tmp.setProfileImageUrl(user.getProfileImageUrl());

            Optional<Follow> relation = followQueryRepository.findRelation(loginUser.getUserId(), f.getFollower().getUserId());



            if(userId == loginUser.getUserId()){
                tmp.setLoginUser(true);
            }



            if(relation.isPresent()){
                tmp.setFollowRelation(true);
            }
            else{
                tmp.setFollowRelation(false);
            }
            followingListDtos.add(tmp);
        }

        return followingListDtos;
    }
}
