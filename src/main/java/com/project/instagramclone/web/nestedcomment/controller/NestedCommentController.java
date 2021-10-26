package com.project.instagramclone.web.nestedcomment.controller;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.NestCommentService;
import com.project.instagramclone.web.follow.dto.FollowCntDto;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentSaveDto;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentUpdateDto;
import com.project.instagramclone.web.user.dto.SuccessResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/api/reply/{parent_id}/")
    public ResponseEntity<SuccessResponseDto> nestedCommentSave(@AuthenticationPrincipal PrincipalDetails userDetails , @PathVariable("parent_id")Long parentId,
                                                                @PathVariable("post_id") Long postId, @RequestBody NestedCommentSaveDto nestedCommentSaveDto){

        User user = userDetails.getUser();
        nestCommentService.nestedCommentSave(user,parentId,nestedCommentSaveDto);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("대댓글 작성 완료").build(), HttpStatus.OK);
    }

    @ApiOperation(value = "대댓글 수정", notes = "대댓글 수정입니다. {comment_id}에는 수정할 대댓글 pk값 입니다.")
    @PutMapping("/api/reply/{comment_id}")
    public ResponseEntity<SuccessResponseDto> nestedCommentUpdate(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("comment_id") Long commentId,
                                    @RequestBody NestedCommentUpdateDto nestedCommentUpdateDto){
        User user = userDetails.getUser();

        nestCommentService.nestedCommentUpdate(user,commentId,nestedCommentUpdateDto);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("대댓글 수정 완료").build(), HttpStatus.OK);

    }

    @ApiOperation(value = "대댓글 삭제", notes = "대댓글 삭제입니다. {comment_id}에는 삭제할 대댓글 pk값 입니다.")
    @DeleteMapping("/api/rely/{comment_id}")
    public ResponseEntity<SuccessResponseDto> nestedCommentDelete(@AuthenticationPrincipal PrincipalDetails userDetails, @PathVariable("comment_id") Long commentId){
        User user = userDetails.getUser();

        nestCommentService.nestedCommentDelete(user,commentId);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("대댓글 삭제 완료").build(), HttpStatus.OK);
    }
}
