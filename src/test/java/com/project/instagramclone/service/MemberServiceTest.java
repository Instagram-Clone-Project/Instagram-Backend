package com.project.instagramclone.service;

import com.project.instagramclone.domain.member.Member;
import com.project.instagramclone.domain.member.MemberRepository;
import com.project.instagramclone.web.member.dto.SignUpRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void signUp() throws Exception {

        //given
        String email = "Test@Test.com";
        String memberName = "Shin";
        String id = "test_shin";
        String password = "test1234!";

        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .email(email)
                .name(memberName)
                .id(id)
                .password(password)
                .build();

        //when
        Long mno = memberService.signUp(signUpRequestDto);

        Optional<Member> member = memberRepository.findById(mno);

        //then
        boolean encodeResult = passwordEncoder.matches(password, member.get().getPassword());

        assertThat(member.get().getEmail()).isEqualTo(email);
        assertThat(member.get().getName()).isEqualTo(memberName);
        assertThat(member.get().getId()).isEqualTo(id);
        assertThat(encodeResult).isEqualTo(true);
    }
}