package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.CommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/{post_id}/comment")
    public void commentSave(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @PathVariable("post_id") Long post_id, @RequestBody CommentSaveDto commentSaveDto){

        User user = userDetails.getUser();
        commentService.commentSave(user,post_id,commentSaveDto);
    }

    @PutMapping("/api/comment/{comment_id}")
    public void commentUpdate(@PathVariable("comment_id") Long commentId, @RequestBody CommentUpdateDto updateDto){
            commentService.commentUpdate(commentId,updateDto);
    }

    @DeleteMapping("/api/comment/{comment_id}")
    public void commentDelete(@PathVariable("comment_id") Long commentId){
         commentService.commentDelete(commentId);
    }




}
