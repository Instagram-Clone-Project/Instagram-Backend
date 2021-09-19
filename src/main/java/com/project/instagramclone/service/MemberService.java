package com.project.instagramclone.service;

import com.project.instagramclone.domain.member.MemberRepository;
import com.project.instagramclone.web.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder encoder;

    @Transactional
    public Long signUp(SignUpRequestDto signUpRequestDto) {

        //암호화된 비밀번호
        String encPassword = encoder.encode(signUpRequestDto.getPassword());

        signUpRequestDto.setPassword(encPassword);

        return memberRepository.save(signUpRequestDto.toEntity()).getMemberId();
    }
}
