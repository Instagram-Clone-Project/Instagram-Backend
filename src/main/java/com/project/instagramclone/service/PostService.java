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
    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getPostId();
    }

    @Transactional
    public void removePost(Post post) {
        postRepository.remove(post);
    }

    @Transactional
    public void updatePost(Long id, String content) {
        Post findPost = postRepository.findOne(id);
        findPost.setContent(content);
        findPost.setModifiedDate(LocalDateTime.now());
    }

    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

}
