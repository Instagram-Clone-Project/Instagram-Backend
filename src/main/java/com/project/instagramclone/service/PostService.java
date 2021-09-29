package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PhotoRepository;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.web.post.dto.CommentDto;
import com.project.instagramclone.web.post.dto.PostShowDto;
import com.project.instagramclone.web.post.dto.PostTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Transactional
    public Long postSave(Post post) {
        postRepository.save(post);
        return post.getPostId();
    }

    @Transactional
    public void removePost(Post post) {
        postRepository.delete(post);
    }

    @Transactional
    public void updatePost(Long id, String content) {
//        Post findPost = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        Post findPost = findOne(id);
        findPost.setContent(content);
//        findPost.setPhoto(photo);
//        findPost.setModifiedDate(LocalDateTime.now());
    }

    public Post findOne(Long postId) {
        return postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
    }

    public List<Post> findAllPost(Long userId) {
        return postRepository.findPostByUsername(userId);
    }

    public List<PostShowDto> getPostList(Long userId) {
        List<PostShowDto> postShowDtoList = new ArrayList<>();

        List<Post> postList = findAllPost(userId);
        for (Post post : postList) {
            List<Photo> photoList = photoRepository.findPhotoListByPostId(post.getPostId());
            PostShowDto postShowDto = new PostShowDto();
            postShowDto.setContent(post.getContent());
            for (Photo photo : photoList) {
                postShowDto.getPhotoList().add(photo.getRoute());
            }

            List<CommentDto> commentList = commentQueryRepository.findCommentByPostId(post.getPostId());
            for (CommentDto commentDto : commentList) {
                postShowDto.getCommentList().add(commentDto);
            }
            postShowDtoList.add(postShowDto);
        }

        return postShowDtoList;
    }

}
