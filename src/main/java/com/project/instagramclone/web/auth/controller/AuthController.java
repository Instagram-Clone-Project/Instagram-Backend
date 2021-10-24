package com.project.instagramclone.web.auth.controller;

import com.project.instagramclone.service.AuthService;
import com.project.instagramclone.service.MailService;
import com.project.instagramclone.web.auth.dto.*;
import com.project.instagramclone.web.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"로그인/회원가입"})
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final MailService mailService;

    @ApiOperation(value = "회원가입 (사용자 정보 작성 후 이메일 인증번호 전송)")
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {

        authService.signUp(signUpRequestDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("회원가입 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입 (이메일 인증번호 일치 여부 확인)")
    @PostMapping("/signup/mail")
    public ResponseEntity<SuccessResponseDto> signUpVerifyAccount(@RequestBody VerifyAccountRequestDto accountRequestDto) {

        mailService.verifyAccount(accountRequestDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("계정 활성화 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "일반 로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 찾기 (이메일 인증번호 전송)")
    @PostMapping("/password/reset")
    public ResponseEntity<SuccessResponseDto> resetPassword(@RequestParam String email) {

        authService.resetPassword(email);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("비밀번호 찾기 - 이메일 인증번호 전송 완료").build(),
                HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 찾기 (인증번호 확인 후 사용자 정보 전송)")
    @PostMapping("/password/reset/mail")
    public ResponseEntity<ResetResponseDto> passwordResetVerifyAccount(@RequestBody ResetRequestDto resetRequestDto) {
        return new ResponseEntity<>(mailService.verifyAccount(resetRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 찾기 (비밀번호 변경)")
    @PostMapping("password/recovery")
    public ResponseEntity<SuccessResponseDto> recoveryPassword(@RequestBody RecoveryRequestDto recoveryRequestDto) {

        authService.recoveryPassword(recoveryRequestDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("비밀번호 변경 완료").build(), HttpStatus.OK);
    }
}
