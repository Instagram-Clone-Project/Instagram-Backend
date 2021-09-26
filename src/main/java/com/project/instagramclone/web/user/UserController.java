package com.project.instagramclone.web.user;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.user.dto.LoginRequestDto;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.signUp(signUpRequestDto);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }


    @GetMapping("test")
    public User test(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return userDetails.getUser();
    }
}
