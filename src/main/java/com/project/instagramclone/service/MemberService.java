package com.project.instagramclone.service;

import com.project.instagramclone.domain.member.Member;
import com.project.instagramclone.domain.member.MemberRepository;
import com.project.instagramclone.security.JwtTokenProvider;
import com.project.instagramclone.web.member.dto.LoginRequestDto;
import com.project.instagramclone.web.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long signUp(SignUpRequestDto signUpRequestDto) {

        //암호화된 비밀번호
        String encPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

        signUpRequestDto.setPassword(encPassword);

        return memberRepository.save(signUpRequestDto.toEntity()).getMemberId();
    }

    @Transactional
    public String login(LoginRequestDto loginRequestDto) {

        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return jwtTokenProvider.generateToken(Long.toString(member.getMemberId()), member.getId(), member.getName());
    }
}
