package com.project.instagramclone.service;

import com.project.instagramclone.domain.follow.Follow;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.domain.follow.FollowRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.web.follow.dto.FollowInfoDto;
import com.project.instagramclone.web.follow.dto.FollowSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        followRepository.save(followSaveDto.toEntity());
    }

    public FollowInfoDto getFollowInfo(Long userId) {
        Long follow = followQueryRepository.getFollow(userId);
        Long follower = followQueryRepository.getFollowing(userId);

        FollowInfoDto followInfoDto = new FollowInfoDto();
        followInfoDto.setFollow(follow);
        followInfoDto.setFollower(follower);

        return followInfoDto;
    }


}
