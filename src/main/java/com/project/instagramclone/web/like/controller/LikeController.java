package com.project.instagramclone.web.like.controller;

import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.LikeService;
import com.project.instagramclone.web.like.dto.LikeResponseDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"좋아요"})
@RequiredArgsConstructor
@RequestMapping("/api/like")
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<LikeResponseDto> like(@PathVariable Long postId,
                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(likeService.like(postId, principalDetails.getUser().getUserId()), HttpStatus.OK);
    }
}
