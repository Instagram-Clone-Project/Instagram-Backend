package com.project.instagramclone.web.user.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.MailService;
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

@Api(tags = {"로그인/회원가입"})
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @ApiOperation(value = "기본 회원가입")
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        userService.signUp(signUpRequestDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("회원가입 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "기본 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "이메일 인증번호 일치 여부 확인")
    @PostMapping("/signup/mail")
    public ResponseEntity<SuccessResponseDto> verifyAccount(@RequestBody VerifyAccountRequestDto accountRequestDto) {

        mailService.verifyAccount(accountRequestDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("계정 활성화가 성공적으로 되었습니다.").build(), HttpStatus.OK);
    }

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
    @PutMapping("/password")
    public ResponseEntity<SuccessResponseDto> updatePassword(@RequestBody PasswordChangeRequestDto passwordChangeRequestDto,
                                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.updatePassword(principalDetails.getUser().getUserId(), passwordChangeRequestDto);

        principalDetails.setUser(user);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("비밀번호 변경 완료").build(), HttpStatus.OK);
    }
}
