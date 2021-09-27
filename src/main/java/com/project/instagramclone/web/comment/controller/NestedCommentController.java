package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.comment.repository.CommentRepository;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.NestCommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/*
   대댓글 관련 로직 컨트롤러
 */

@RestController
@RequiredArgsConstructor
@Api(tags = {"대댓글"})
public class NestedCommentController {

    private final NestCommentService nestCommentService;

    @ApiOperation(value = "대댓글 작성", notes = "대댓글 작성입니다. {post_id}에는 대댓글을 작성할 게시글 pk입니다.  {parent_id}에는 대댓글을 작성할 부모 댓글 pk값입니다.")
    @PostMapping("/api/{post_id}/{parent_id}/nestedcomment")
    public void nestedCommentSave(@AuthenticationPrincipal UserDetailsImpl userDetails ,@PathVariable("parent_id")Long parentId,
                                  @PathVariable("post_id") Long postId,@RequestBody CommentSaveDto commentSaveDto){

        User user = userDetails.getUser();

        nestCommentService.nestedCommentSave(user, postId,parentId, commentSaveDto);
    }

    @ApiOperation(value = "대댓글 수정", notes = "대댓글 수정입니다. {comment_id}에는 수정할 대댓글 pk값 입니다.")
    @PutMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentUpdate(@PathVariable("comment_id") Long commentId,
                                    @RequestBody CommentUpdateDto commentUpdateDto){
        nestCommentService.nestedCommentUpdate(commentId,commentUpdateDto);
    }

    @ApiOperation(value = "대댓글 삭제", notes = "대댓글 삭제입니다. {comment_id}에는 삭제할 대댓글 pk값 입니다.")
    @DeleteMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentDelete(@PathVariable("comment_id") Long commentId){
        nestCommentService.nestedCommetDelete(commentId);
    }
}