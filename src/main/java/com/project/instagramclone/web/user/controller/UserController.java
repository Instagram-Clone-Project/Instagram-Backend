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
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        userService.signUp(signUpRequestDto);

        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }

    @ApiOperation(value = "기본 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "이메일 인증번호 일치 여부 확인")
    @PostMapping("/signup/mail")
    public ResponseEntity<String> verifyAccount(@RequestBody VerifyAccountRequestDto accountRequestDto) {

        mailService.verifyAccount(accountRequestDto);

        return new ResponseEntity<>("계정 활성화가 성공적으로 되었습니다.", HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 편집")
    @PutMapping("/{userId}/update")
    public ResponseEntity<String> update(@PathVariable("userId") Long userId,
                                         @RequestBody UserRequestDto userRequestDto,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.update(userId, userRequestDto);

        principalDetails.setUser(user);

        return new ResponseEntity<>("회원수정 완료", HttpStatus.OK);
    }

    @ApiOperation(value = "프로필 사진 수정")
    @PostMapping("/{userId}/update/image")
    public ResponseEntity<String> uploadProfileImage(@PathVariable("userId") Long userId,
                                     @RequestPart MultipartFile profileImageFile,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.uploadProfileImage(userId, profileImageFile);

        principalDetails.setUser(user);

        return new ResponseEntity<>("프로필 사진수정 완료", HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 변경")
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updatePassword(@PathVariable("userId") Long userId,
                                                 @RequestBody PasswordChangeRequestDto passwordChangeRequestDto,
                                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = userService.updatePassword(userId, passwordChangeRequestDto);

        principalDetails.setUser(user);

        return new ResponseEntity<>("비밀번호 변경 완료", HttpStatus.OK);
    }
}
