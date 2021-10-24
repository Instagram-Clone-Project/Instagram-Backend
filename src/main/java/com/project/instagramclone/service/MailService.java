package com.project.instagramclone.service;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.auth.dto.ResetRequestDto;
import com.project.instagramclone.web.auth.dto.ResetResponseDto;
import com.project.instagramclone.web.auth.dto.VerifyAccountRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Async
    public void sendMail(String email, String authCode) throws MessagingException {
        /*
        setFrom: 보내는 사람
        setTo: 받는 사람
        setSubject: 메일 제목
        setText: 메일 내용 (true 넣을 경우 html)
         */
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom(from, "인스타그램");
            helper.setTo(email);
            helper.setSubject("[인스타그램 회원가입 이메일 인증]");

            helper.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                    .append("<p>인증 번호는 ").append(authCode).append("입니다.</p>").toString(), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String generateAuthCode() {

        Random random = new Random();
        StringBuffer authCode = new StringBuffer();
        int num = 0;

        while (authCode.length() < 6) {
            num = random.nextInt(10);

            authCode.append(num);
        }

        return authCode.toString();
    }

    @Transactional
    public void verifyAccount(VerifyAccountRequestDto accountRequestDto) {

        User user = userRepository.findByEmail(accountRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.getVerificationCode().equals(accountRequestDto.getAuthCode())) {
            user.changeEnabled(true);
        } else {
            throw new CustomException(ErrorCode.MISMATCH_AUTH_CODE);
        }
    }

    @Transactional
    public ResetResponseDto verifyAccount(ResetRequestDto resetRequestDto) {

        User user = userRepository.findByEmail(resetRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.getVerificationCode().equals(resetRequestDto.getAuthCode())) {
            return ResetResponseDto.builder()
                    .profileImageUrl(user.getProfileImageUrl())
                    .username(user.getUsername())
                    .build();
        } else {
            throw new CustomException(ErrorCode.MISMATCH_AUTH_CODE);
        }
    }
}
