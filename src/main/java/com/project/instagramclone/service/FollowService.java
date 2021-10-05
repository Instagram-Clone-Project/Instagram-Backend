package com.project.instagramclone.service;

import com.project.instagramclone.domain.follow.Follow;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.domain.follow.FollowRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.web.follow.dto.FollowCntDto;
import com.project.instagramclone.web.follow.dto.FollowSaveDto;
import com.project.instagramclone.web.follow.dto.FollowerListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Long follow = followQueryRepository.getFollow(userId);
        Long follower = followQueryRepository.getFollowing(userId);

        FollowCntDto followCntDto = new FollowCntDto();
        followCntDto.setFollowing(follow);
        followCntDto.setFollowing(follower);

        return followCntDto;
    }

    @Transactional
    public List<FollowerListDto> getFollowerList(Long userId, User loginUser) {
        List<Follow> followList = followQueryRepository.findFollowList(userId);

        System.out.println(followList.size());
        List<FollowerListDto> followerListDtos = new ArrayList<>();

        for(Follow f : followList){
            if(f.getFollowing().getUserId() == loginUser.getUserId()) continue; // 자기 자신 스킵

            FollowerListDto tmp = new FollowerListDto();

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
}
