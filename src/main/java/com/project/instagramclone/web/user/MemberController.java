package com.project.instagramclone.web.user;

import com.project.instagramclone.service.MemberService;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Long signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return memberService.save(signUpRequestDto);
    }
}
