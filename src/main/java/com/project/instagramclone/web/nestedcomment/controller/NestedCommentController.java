package com.project.instagramclone.web.nestedcomment.controller;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.service.NestCommentService;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentSaveDto;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentUpdateDto;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
                                  @PathVariable("post_id") Long postId,@RequestBody NestedCommentSaveDto nestedCommentSaveDto){

        User user = userDetails.getUser();

        nestCommentService.nestedCommentSave(user, postId,parentId, nestedCommentSaveDto);
    }

    @ApiOperation(value = "대댓글 수정", notes = "대댓글 수정입니다. {comment_id}에는 수정할 대댓글 pk값 입니다.")
    @PutMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentUpdate(@PathVariable("comment_id") Long commentId,
                                    @RequestBody NestedCommentUpdateDto nestedCommentUpdateDto){
        nestCommentService.nestedCommentUpdate(commentId,nestedCommentUpdateDto);
    }

    @ApiOperation(value = "대댓글 삭제", notes = "대댓글 삭제입니다. {comment_id}에는 삭제할 대댓글 pk값 입니다.")
    @DeleteMapping("/api/{comment_id}/nestedcomment")
    public void nestedCommentDelete(@PathVariable("comment_id") Long commentId){
        nestCommentService.nestedCommentDelete(commentId);
    }

    private final CommentQueryRepository commentQueryRepository;



}
