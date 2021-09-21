package com.project.instagramclone.service;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

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

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

}
