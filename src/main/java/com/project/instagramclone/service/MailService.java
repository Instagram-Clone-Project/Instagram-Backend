package com.project.instagramclone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    @Async
    public void sendMail(String email) throws MessagingException {

        String authCode = generateAuthCode();

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
}
