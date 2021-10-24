package com.project.instagramclone.service;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.security.JwtTokenProvider;
import com.project.instagramclone.web.auth.dto.LoginRequestDto;
import com.project.instagramclone.web.auth.dto.LoginResponseDto;
import com.project.instagramclone.web.auth.dto.RecoveryRequestDto;
import com.project.instagramclone.web.auth.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {

        String email = signUpRequestDto.getEmail();
        String username = signUpRequestDto.getUsername();

        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        } if (userRepository.existsByUsername(username)) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }
        //암호화된 비밀번호
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        signUpRequestDto.setPassword(encPassword);
        //DB 내 User 테이블에 회원 저장 (회원가입 o, 활성화 x)
        User user = userRepository.save(signUpRequestDto.toEntity());
        //인증코드 생성
        String authCode = mailService.generateAuthCode();

        user.changeVerificationCode(authCode);
        user.changeEnabled(false);
        //인증코드 전송
        try {
            mailService.sendMail(signUpRequestDto.getEmail(), authCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //첫 번째 조건문: 비밀번호 일치 여부
        //두 번째 조건문: (회원가입 시 정상적인) 이메일 인증 -> 활성화 여부
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_PASSWORD);
        } if (!user.isEnabled()) {
            throw new CustomException(ErrorCode.NOT_CERTIFIED_EMAIL);
        }

        return LoginResponseDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .accessToken(jwtTokenProvider.generateToken(user.getUsername()))
                .build();
    }

    @Transactional
    public void resetPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        String authCode = mailService.generateAuthCode();

        user.changeVerificationCode(authCode);
        //인증코드 전송
        try {
            mailService.sendMail(email, authCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void recoveryPassword(RecoveryRequestDto recoveryRequestDto) {

        User user = userRepository.findByEmail(recoveryRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updatePassword(passwordEncoder.encode(recoveryRequestDto.getNewPassword()));
    }
}
