package com.project.instagramclone.service;

import com.project.instagramclone.domain.follow.Follow;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.domain.follow.FollowRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
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
        User toUser = userRepository.findById(toUserId).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다"));

        FollowSaveDto followSaveDto = new FollowSaveDto();
        followSaveDto.setFromUser(fromUser);
        followSaveDto.setToUser(toUser);

        Follow follow = followSaveDto.toEntity();

        followRepository.save(followSaveDto.toEntity());
    }

    @Transactional
    public void delete(User fromUser, Long toUserId){
        User toUser = userRepository.findById(toUserId).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다"));

        Follow followTableId = followQueryRepository.findFollowTableId(fromUser.getUserId(), toUserId)
                .orElseThrow(()-> new IllegalArgumentException("해당 팔로우 정보가 없습니다"));

        followRepository.delete(followTableId);
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
            log.info(f.getFollowing().getUserId()+" "+f.getFollwer().getUserId());
            User user = userRepository.findById(f.getFollowing().getUserId()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다"));

            tmp.setUsername(user.getUsername());
            tmp.setProfileImageUrl(user.getProfileImageUrl());
            Optional<Follow> relation = followQueryRepository.findRelation(loginUser.getUserId(), f.getFollowing().getUserId());

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
        log.info("실행");

        // 지금 현재 테이블에는 userId를 팔로잉 하는 테이블 리스트의 정보가 나온다.
        // 그렇다면 Follow (Following = userId Follower = 다른사람)

        for(Follow f : followingList){
            if(f.getFollwer().getUserId() == loginUser.getUserId()) continue; // 자기 자신 스킵

            FollowingListDto tmp = new FollowingListDto();

            User user = userRepository.findById(f.getFollwer().getUserId()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다"));
            log.info(loginUser.getUserId()+" "+f.getFollowing().getUserId() +" "+f.getFollwer().getUserId());

            tmp.setUsername(user.getUsername());
            tmp.setProfileImageUrl(user.getProfileImageUrl());

            Optional<Follow> relation = followQueryRepository.findRelation(loginUser.getUserId(), f.getFollwer().getUserId());

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
