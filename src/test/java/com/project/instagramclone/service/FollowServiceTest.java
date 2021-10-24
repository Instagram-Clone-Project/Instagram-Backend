package com.project.instagramclone.service;

import com.project.instagramclone.domain.follow.FollowRepository;
import com.project.instagramclone.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource("classpath:application_test.properties")
@Transactional
class FollowServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired FollowService followService;
    @Autowired FollowRepository followRepository;

    @BeforeAll
    public void beforeAll(){
        //given
        String email = "Test1@Test.com";
        String memberName = "Mo";
        String username = "Mo";
        String password = "test1234!";

//        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder()
//                .email(email)
//                .name(memberName)
//                .username(username)
//                .password(password)
//                .build();


        String email2 = "Test2@Test.com";
        String memberName2 = "Su";
        String username2 = "Su";
        String password2 = "test1234!";

//        SignUpRequestDto signUpRequestDto2 = SignUpRequestDto.builder()
//                .email(email2)
//                .name(memberName2)
//                .username(username2)
//                .password(password2)
//                .build();
//        userRepository.save(signUpRequestDto.toEntity());
//        userRepository.save(signUpRequestDto2.toEntity());
    }
}