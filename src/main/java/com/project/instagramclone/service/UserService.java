package com.project.instagramclone.service;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.security.JwtTokenProvider;
import com.project.instagramclone.web.user.dto.LoginRequestDto;
import com.project.instagramclone.web.user.dto.LoginResponseDto;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import com.project.instagramclone.web.user.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;
    private final FileUploadService fileUploadService;

    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) throws MessagingException {

        //암호화된 비밀번호
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        signUpRequestDto.setPassword(encPassword);

        User user = userRepository.save(signUpRequestDto.toEntity());

        String authCode = mailService.generateAuthCode();

        user.createVerificationCode(authCode);
        user.changeEnabled(false);

        mailService.sendMail(signUpRequestDto.getEmail(), authCode);
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .username(user.getUsername())
                .profileImageUrl(user.getProfileImageUrl())
                .accessToken(jwtTokenProvider.generateToken(Long.toString(user.getUserId()), user.getUsername(), user.getName()))
                .build();

        return loginResponseDto;
    }

    @Transactional
    public User update(Long userId, UserRequestDto userRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("찾을 수 없는 id 입니다."));

        user.updateUser(userRequestDto);

        return user;
    }

    @Transactional
    public User uploadProfileImage(Long userId, MultipartFile profileImageFile) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 id 입니다."));

        String profileImageUrl = fileUploadService.uploadImage(profileImageFile);

        user.updateProfileImage(profileImageUrl);

        return user;
    }
}
