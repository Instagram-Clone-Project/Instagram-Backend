package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.comment.repository.CommentRepository;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.NestCommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/*
   대댓글 관련 로직 컨트롤러
 */

@RestController
@RequiredArgsConstructor
public class NestedCommentController {

    private final NestCommentService nestCommentService;

    @PostMapping("/api/{parent_id}/nestedcomment")
    public void nestedCommentSave(@AuthenticationPrincipal UserDetailsImpl userDetails ,@PathVariable("parent_id")Long parentId,
                                  @RequestBody CommentSaveDto commentSaveDto){

        User user = userDetails.getUser();

        nestCommentService.nestedCommentSave(user, parentId, commentSaveDto);
    }

    @PutMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentUpdate(@PathVariable("comment_id") Long commentId,
                                    @RequestBody CommentUpdateDto commentUpdateDto){
        nestCommentService.nestedCommentUpdate(commentId,commentUpdateDto);
    }

    @DeleteMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentDelete(@PathVariable("comment_id") Long commentId){
        nestCommentService.nestedCommetDelete(commentId);
    }
}
