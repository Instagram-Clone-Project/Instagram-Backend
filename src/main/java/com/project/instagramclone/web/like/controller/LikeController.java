package com.project.instagramclone.web.like.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.LikeService;
import com.project.instagramclone.web.activity.dto.ActivityDto;
import com.project.instagramclone.web.like.dto.LikeResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"좋아요 기능"})
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LikeController {

    private final LikeService likeService;

    @ApiOperation(value = "게시물 좋아요")
    @GetMapping("/post/{postId}/like")
    public ResponseEntity<LikeResponseDto> likePost(@PathVariable Long postId,
                                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(likeService.likePost(postId, principalDetails.getUser()), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 좋아요")
    @GetMapping("/comment/{commentId}/like")
    public ResponseEntity<LikeResponseDto> likeComment(@PathVariable Long commentId,
                                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(likeService.likeComment(commentId, principalDetails.getUser()), HttpStatus.OK);
    }

    @ApiOperation(value = "답글 좋아요")
    @GetMapping("/reply/{replyId}/like")
    public ResponseEntity<LikeResponseDto> likeReply(@PathVariable Long replyId,
                                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(likeService.likeReply(replyId, principalDetails.getUser()), HttpStatus.OK);
    }


//    @ApiOperation(value = "좋아요 알림 목록 확인")
//    public ResponseEntity<>
}
