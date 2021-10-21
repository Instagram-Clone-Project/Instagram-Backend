package com.project.instagramclone.web.follow.controller;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.FollowService;
import com.project.instagramclone.web.follow.dto.FollowCntDto;
import com.project.instagramclone.web.follow.dto.FollowerListDto;
import com.project.instagramclone.web.follow.dto.FollowingListDto;
import com.project.instagramclone.web.user.dto.SuccessResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = {"팔로우"})
public class FollowController {
    private final FollowQueryRepository followQueryRepository;
    private final FollowService followService;

    @ApiOperation(value = "팔로우", notes = "팔로우 기능입니다. {toUserId}에는 팔로우 할 대상의 userId 값 입니다.")
    @PostMapping("/api/follow/{toUserId}")
    public ResponseEntity<SuccessResponseDto> save(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("toUserId") Long toUserId){

        followService.save(userDetails.getUser(), toUserId);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("팔로우 완료").build(), HttpStatus.OK);
    }


    @ApiOperation(value = "팔로우 목록 조회", notes = "{user_id}에 해당하는 유저의 팔로우,팔로워 수를 보여줍니다.")
    @GetMapping("/api/follow/{user_id}")
    public ResponseEntity<FollowCntDto> followList(@PathVariable("user_id") Long userId){
        return new ResponseEntity<>(followService.getFollowInfo(userId),HttpStatus.OK);
    }

    @ApiOperation(value = "언팔로우", notes = "{toUserId}에 해당하는 유저를 언팔로우 합니다.")
    @DeleteMapping("/api/unfollow/{toUserId}")
    public ResponseEntity<SuccessResponseDto> delete(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("toUserId") Long toUserId){
        followService.delete(userDetails.getUser(), toUserId);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("언팔로우 완료").build(), HttpStatus.OK);

    }

    @ApiOperation(value = "팔로워목록", notes = "{userId}에 해당하는 유저의 팔로워목록을 보여줍니다.")
    @GetMapping("/api/follower/{user_id}")
    public List<FollowerListDto> getFollowerList(@PathVariable("user_id") Long userId ,@AuthenticationPrincipal PrincipalDetails userDetails){

        return followService.getFollowerList(userId,userDetails.getUser());
    }

    @ApiOperation(value = "팔로잉목록", notes = "{userId}에 해당하는 유저의 팔로잉목록을 보여줍니다.")
    @GetMapping("/api/following/{user_id}")
    public List<FollowingListDto> getFollowingList(@PathVariable("user_id")Long userId,@AuthenticationPrincipal PrincipalDetails userDetails){
        return followService.getFollowingList(userId,userDetails.getUser());
    }
}
