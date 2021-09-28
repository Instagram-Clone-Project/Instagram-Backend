package com.project.instagramclone.web.user.controller;

import com.project.instagramclone.service.MailService;
import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.user.dto.LoginRequestDto;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import com.project.instagramclone.web.user.dto.VerifyAccountRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Api(tags = {"로그인/회원가입"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @ApiOperation(value = "기본 회원가입")
    @PostMapping("/accounts/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws MessagingException {

        userService.signUp(signUpRequestDto);

        return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
    }

    @ApiOperation(value = "기본 로그인")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @ApiOperation(value = "이메일 인증번호 일치 여부 확인")
    @PostMapping("/accounts/signup/mail")
    public ResponseEntity<String> verifyAccount(@RequestBody VerifyAccountRequestDto accountRequestDto) {

        mailService.verifyAccount(accountRequestDto);

        return new ResponseEntity<>("계정 활성화가 성공적으로 되었습니다.", HttpStatus.OK);
    }
}
