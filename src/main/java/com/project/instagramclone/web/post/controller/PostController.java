package com.project.instagramclone.web.post.controller;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.service.*;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.web.post.dto.PostSaveDto;
import com.project.instagramclone.web.post.dto.PostShowDto;
import com.project.instagramclone.web.post.dto.PostUpdateDto;
import com.project.instagramclone.web.user.dto.SuccessResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"게시글"})
public class PostController {

    private final PostService postService;
    private final PhotoService photoService;
    private final CommentService commentService;
    private final FileUploadService fileUploadService;


    @ApiOperation(value = "게시글 수정", notes = "게시글 수정입니다.")
    @PutMapping("/api/post/{post_id}")
    public ResponseEntity<SuccessResponseDto> postUpdate(
            @AuthenticationPrincipal PrincipalDetails userDetails,
            @RequestBody PostUpdateDto postUpdateDto, @PathVariable("post_id") Long postId){

        User user = userDetails.getUser();
        postService.postUpdate(postUpdateDto,user,postId);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("수정이 완료되었습니다").build(),HttpStatus.OK);
    }


    @ApiOperation(value = "게시글 작성", notes = "게시글 작성입니다.")
    @PostMapping("/api/post")
    public ResponseEntity<SuccessResponseDto> postSave(@RequestBody PostSaveDto postSaveDto, @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {

        Post post = postSaveDto.toEntity();
        User user = userDetails.getUser();
        post.setUser(user);

        List<MultipartFile> files = postSaveDto.getFiles();
        for (MultipartFile file : files) {
            fileUploadService.uploadImageToPost(file, post);
        }

        postService.postSave(post);

        return new ResponseEntity<>(SuccessResponseDto.builder().message("글 등록이 완료되었습니다").build(),HttpStatus.OK);
    }


    @ApiOperation(value = "게시글 삭제", notes = "{post_id}에는 지워질 post의 pk값입니다. post와 연관된 photo 데이터, comment 데이터도 삭제됩니다.")
    @DeleteMapping("/api/post/{post_id}")
    public ResponseEntity<SuccessResponseDto> postDelete(@AuthenticationPrincipal PrincipalDetails userDetails,@PathVariable("post_id") Long postId) {
        User user = userDetails.getUser();
        postService.deletePost(postId,user);
        return new ResponseEntity<>(SuccessResponseDto.builder().message("삭제가 완료되었습니다").build(),HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/api/post")
    public List<PostShowDto> postAllShow(@AuthenticationPrincipal PrincipalDetails userDetails) {
        User user = userDetails.getUser();
        return postService.getPostList(user.getUserId());
    }
}
