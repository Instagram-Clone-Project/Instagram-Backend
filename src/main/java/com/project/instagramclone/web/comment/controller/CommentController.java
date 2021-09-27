package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.CommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"댓글"})
public class CommentController {

    private final CommentService commentService;
    @ApiOperation(value = "댓글 작성", notes = "댓글 작성입니다. {post_id}에는 댓글을 작성할 게시글 pk값입니다.")
    @PostMapping("/api/comment/{post_id}")
    public void commentSave(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @PathVariable("post_id") Long post_id, @RequestBody CommentSaveDto commentSaveDto){

        User user = userDetails.getUser();
        commentService.commentSave(user,post_id,commentSaveDto);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글 수정입니다. {comment_id}에는 댓글을 수정할 댓글 pk값입니다.")
    @PutMapping("/api/comment/{comment_id}")
    public void commentUpdate(@PathVariable("comment_id") Long commentId, @RequestBody CommentUpdateDto updateDto){

        commentService.commentUpdate(commentId,updateDto);
    }
    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제입니다. {comment_id}에는 댓글을 삭제할 댓글 pk값입니다.")
    @DeleteMapping("/api/comment/{comment_id}")
    public void commentDelete(@PathVariable("comment_id") Long commentId){
         commentService.commentDelete(commentId);
    }




}