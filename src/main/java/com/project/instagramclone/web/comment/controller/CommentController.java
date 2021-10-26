package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.CommentService;
import com.project.instagramclone.web.comment.dto.CommentGetDto;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.project.instagramclone.web.user.dto.SuccessResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"댓글"})
public class CommentController {
    private final CommentQueryRepository commentQueryRepository;
    private final CommentService commentService;
    @ApiOperation(value = "댓글 작성", notes = "댓글 작성입니다. {post_id}에는 댓글을 작성할 게시글 pk값입니다.")
    @PostMapping("/api/comment/{post_id}")
    public ResponseEntity<SuccessResponseDto> commentSave(@AuthenticationPrincipal PrincipalDetails userDetails,
                                                          @PathVariable("post_id") Long post_id, @RequestBody CommentSaveDto commentSaveDto){

        User user = userDetails.getUser();
        commentService.commentSave(user,post_id,commentSaveDto);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("댓글 저장완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글 수정입니다. {comment_id}에는 댓글을 수정할 댓글 pk값입니다.")
    @PutMapping("/api/comment/{comment_id}")
    public ResponseEntity<SuccessResponseDto>  commentUpdate(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("comment_id") Long commentId, @RequestBody CommentUpdateDto updateDto){
        User user = userDetails.getUser();
        commentService.commentUpdate(commentId,updateDto,user);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("댓글 수정완료").build(), HttpStatus.OK);

    }
    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제입니다. {comment_id}에는 댓글을 삭제할 댓글 pk값입니다.")
    @DeleteMapping("/api/comment/{comment_id}")
    public ResponseEntity<SuccessResponseDto> commentDelete(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("comment_id") Long commentId){

        User user = userDetails.getUser();
        commentService.commentDelete(commentId,user);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("댓글 삭제완료").build(), HttpStatus.OK);

    }


//    @GetMapping("/api/comment/{post_id}")
//    @ApiOperation(value = "댓글 조회", notes = "댓글 조회입니다. {post_id}에는 댓글을 조회할 게시글 pk값입니다.")
//    public ResponseEntity<CommentGetDto> getComments(@PathVariable("post_id") Long postId){
//        return new ResponseEntity<>(commentService.getComments(postId), HttpStatus.OK);
//    }
}
