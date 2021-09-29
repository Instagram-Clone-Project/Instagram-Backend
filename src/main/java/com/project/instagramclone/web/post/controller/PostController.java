package com.project.instagramclone.web.post.controller;

import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.PrincipalDetails;
import com.project.instagramclone.service.PhotoService;
import com.project.instagramclone.service.PostService;
import com.project.instagramclone.service.S3UploadService;
import com.project.instagramclone.web.post.dto.PostSaveDto;
import com.project.instagramclone.web.post.dto.PostShowDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"게시글"})
public class PostController {

    private final PostService postService;
    private final PhotoService photoService;
    private final S3UploadService s3UploadService;

    @ApiOperation(value = "게시글 작성", notes = "게시글 작성입니다.")
    @PostMapping("/api/post")
    public void postSave(@ModelAttribute PostSaveDto postSaveDto, @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {
        Post post = new Post();
        post.setContent(postSaveDto.getContent());

        User user = userDetails.getUser();
        post.setUser(user);

        List<MultipartFile> files = postSaveDto.getFiles();
        for (MultipartFile file : files) {
            s3UploadService.uploadSave(file, "static", post);
        }

        postService.postSave(post);
    }


    @ApiOperation(value = "게시글 삭제", notes = "{post_id}에는 지워질 post의 pk값입니다. post와 연관된 photo 데이터, comment 데이터도 삭제됩니다.")
    @DeleteMapping("/api/post/{post_id}")
    public void postDelete(@PathVariable("post_id") Long postId) {
        Post findPost = postService.findOne(postId);
        postService.removePost(findPost);
    }

    @GetMapping("/api/post")
    public List<PostShowDto> postAllShow(@AuthenticationPrincipal PrincipalDetails userDetails) {

        User user = userDetails.getUser();

        List<PostShowDto> postShowDtoList = new ArrayList<>();
        List<Post> postList = postService.findAllPost(user.getUserId());
        for (Post post : postList) {
            List<Photo> photoList = photoService.findAllPhotoByPostId(post.getPostId());
            PostShowDto postShowDto = new PostShowDto();
            postShowDto.setContent(post.getContent());
            for (Photo photo : photoList) {
                postShowDto.getPhotoList().add(photo.getRoute());
            }
            postShowDtoList.add(postShowDto);
        }

        return postShowDtoList;
    }

//    @GetMapping("/api/post/{post_id}")
//    public void postShow(@PathVariable("post_id") Long postId) {
//        Post findPost = postService.findOne(postId);
//    }


    /**
     * 수정 프로세스 알고난 후 진행 예정
     */
//    @PutMapping("/api/post/{post_id}")
//    public void postUpdate(@RequestBody PostUpdateDto postUpdateDto, @PathVariable("post_id") Long postId) {
//        postService.updatePost(postId, postUpdateDto.getContent());
//
//    }


}
