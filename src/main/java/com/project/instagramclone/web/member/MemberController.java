package com.project.instagramclone.web.member;

import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.member.dto.LoginRequestDto;
import com.project.instagramclone.web.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }
}
