package com.project.instagramclone.web.follow.controller;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.FollowService;
import com.project.instagramclone.web.follow.dto.FollowCntDto;
import com.project.instagramclone.web.follow.dto.FollowerListDto;
import com.project.instagramclone.web.follow.dto.FollowingListDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"팔로우"})
public class FollowController {
    private final FollowQueryRepository followQueryRepository;
    private final FollowService followService;

    @ApiOperation(value = "팔로우", notes = "팔로우 기능입니다. {toUserId}에는 팔로우 할 대상의 userId 값 입니다.")
    @PostMapping("/api/follow/{toUserId}")
    public void save(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("toUserId") Long toUserId){

        followService.save(userDetails.getUser(), toUserId);
    }


    @ApiOperation(value = "팔로우 목록 조회", notes = "{user_id}에 해당하는 유저의 팔로우,팔로워 수를 보여줍니다.")
    @GetMapping("/api/follow/{user_id}")
    public FollowCntDto followList(@PathVariable("user_id") Long userId){
        return followService.getFollowInfo(userId);
    }

    @DeleteMapping("/api/follow/{toUserId}")
    public void delete(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("toUserId") Long toUserId){
        followService.delete(userDetails.getUser(), toUserId);
    }

    @GetMapping("/api/follower/list/{user_id}")
    public List<FollowerListDto> getFollowerList(@PathVariable("user_id") Long userId ,@AuthenticationPrincipal PrincipalDetails userDetails){
        return followService.getFollowerList(userId,userDetails.getUser());
    }

    @GetMapping("/api/following/list/{user_id}")
    public List<FollowingListDto> getFollowingList(@PathVariable("user_id")Long userId,@AuthenticationPrincipal PrincipalDetails userDetails){
        return followService.getFollowingList(userId,userDetails.getUser());
    }
}
