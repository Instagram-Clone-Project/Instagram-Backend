package com.project.instagramclone.web.home.controller;

import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.home.dto.homeResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class homeController {

    private final UserService userService;

    @ApiOperation("인스타그램 홈페이지")
    @GetMapping("/")
    public homeResponseDto home(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(userService.home(principalDetails.getUser()), HttpStatus.OK);
    }
}
