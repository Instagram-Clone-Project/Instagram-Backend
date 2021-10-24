package com.project.instagramclone.web.user.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(tags = {"프로필"})
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "프로필 편집 (기본 정보)")
    @GetMapping("/profile/edit")
    public ResponseEntity<EditResponseDto> info(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(userService.info(principalDetails.getUser().getUserId()), HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 편집 (수정)")
    @PutMapping("/profile/edit")
    public ResponseEntity<SuccessResponseDto> update(@RequestBody EditRequestDto editRequestDto,
                                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.update(principalDetails.getUser().getUserId(), editRequestDto);

        principalDetails.setUser(user);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("회원수정 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 사진 수정")
    @PostMapping("/profile/image")
    public ResponseEntity<SuccessResponseDto> uploadProfileImage(@RequestPart MultipartFile profileImageFile,
                                                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.uploadProfileImage(principalDetails.getUser().getUserId(), profileImageFile);

        principalDetails.setUser(user);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("프로필 사진수정 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 사진 삭제")
    @DeleteMapping("/profile/image")
    public ResponseEntity<SuccessResponseDto> deleteProfileImage(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.deleteProfileImage(principalDetails.getUser().getUserId(), principalDetails.getUser().getProfileImageUrl());

        principalDetails.setUser(user);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("프로필 사진삭제 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 변경")
    @PutMapping("/password/change")
    public ResponseEntity<SuccessResponseDto> updatePassword(@Valid @RequestBody PasswordChangeRequestDto passwordChangeRequestDto,
                                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.updatePassword(principalDetails.getUser().getUserId(), passwordChangeRequestDto);

        principalDetails.setUser(user);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("비밀번호 변경 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "메인 프로필")
    @GetMapping("/profile/{username}")
    public ResponseEntity<ProfileResponseDto> mainProfile(@PathVariable String username,
                                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(userService.mainProfile(username), HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 팔로잉 목록")
    @GetMapping("/profile/{username}/followings")
    public ResponseEntity<FollowingsResponseDto> getFollowings(@PathVariable String username,
                                                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(userService.getFollowings(username, principalDetails.getUser()), HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 팔로워 목록")
    @GetMapping("/profile/{username}/followers")
    public ResponseEntity<FollowersResponseDto> getFollowers(@PathVariable String username,
                                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(userService.getFollowers(username, principalDetails.getUser()), HttpStatus.OK);
    }
}
