package com.project.instagramclone.service;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void signUp() throws Exception {

        //given
        String email = "Test@Test.com";
        String memberName = "Shin";
        String username = "test_shin";
        String password = "test1234!";

        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
                .email(email)
                .name(memberName)
                .username(username)
                .password(password)
                .build();

        //when
        userService.signUp(signUpRequestDto);

        Long mno = userRepository.findByUsername(username).get().getUserId();

        Optional<User> member = userRepository.findById(mno);

        //then
        boolean encodeResult = passwordEncoder.matches(password, member.get().getPassword());

        assertThat(member.get().getEmail()).isEqualTo(email);
        assertThat(member.get().getName()).isEqualTo(memberName);
        assertThat(member.get().getUsername()).isEqualTo(username);
        assertThat(encodeResult).isEqualTo(true);
    }
}