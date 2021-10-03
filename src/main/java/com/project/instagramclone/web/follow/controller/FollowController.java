package com.project.instagramclone.web.follow.controller;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.FollowService;
import com.project.instagramclone.web.follow.dto.FollowInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowQueryRepository followQueryRepository;
    private final FollowService followService;

    @PostMapping("/api/follow/{toUserId}")
    public void save(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("toUserId") Long toUserId){

        followService.save(userDetails.getUser(), toUserId);
    }


    @GetMapping("/api/follow/{user_id}")
    public FollowInfoDto followTest(@PathVariable("user_id") Long userId){
        return followService.getFollowInfo(userId);
    }
}
