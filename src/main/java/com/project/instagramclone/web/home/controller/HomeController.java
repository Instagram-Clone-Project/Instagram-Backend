package com.project.instagramclone.web.home.controller;

import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.HomeService;
import com.project.instagramclone.service.UserService;
import com.project.instagramclone.web.home.dto.HomeResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"홈페이지"})
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeService homeService;

    @ApiOperation("인스타그램 홈페이지")
    @GetMapping("/")
    public ResponseEntity<HomeResponseDto> home(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(homeService.home(principalDetails.getUser()), HttpStatus.OK);
    }
}
