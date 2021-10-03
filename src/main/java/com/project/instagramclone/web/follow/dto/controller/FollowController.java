//package com.project.instagramclone.web.follow.dto.controller;
//
//import com.project.instagramclone.domain.user.User;
//import com.project.instagramclone.security.UserDetailsImpl;
//import com.project.instagramclone.service.FollowService;
//import com.project.instagramclone.web.follow.dto.FollowSaveDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class FollowController {
//
//    private final FollowService followService;
//
//    @PostMapping("/api/follow/{toUserId}")
//    public void save(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("toUserId") Long toUserId){
//
//        followService.save(userDetails.getUser(), toUserId);
//    }
//}
