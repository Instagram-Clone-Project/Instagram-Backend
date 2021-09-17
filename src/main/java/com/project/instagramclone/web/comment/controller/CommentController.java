package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.reply.entity.Comment;
import com.project.instagramclone.service.CommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/{post_id}/comment")
    public Comment commentSave(@RequestBody CommentSaveDto commentSaveDto){

        return commentService.commentSave(commentSaveDto);
    }

    @PutMapping("/api/comment/{comment_id}")
    public Comment commentUpdate(@PathVariable("comment_id") Long commentId, @RequestBody CommentUpdateDto updateDto){
        return commentService.commentUpdate(commentId,updateDto);
    }

    @DeleteMapping("/api/comment/{comment_id}")
    public void commentDelete(@PathVariable("comment_id") Long commentId){
         commentService.commetDelete(commentId);
    }
}
