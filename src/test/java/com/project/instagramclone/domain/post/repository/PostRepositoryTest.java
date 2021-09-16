package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired PostRepository postRepository;

    @Test
    @Transactional
    public void testPost() {
        Post post = new Post();
        post.setContent("테스트 코멘트");

        Long saveId = postRepository.save(post);
        Post findPost = postRepository.findOne(saveId);

        Assertions.assertThat(findPost.getPostId()).isEqualTo(post.getPostId());
        Assertions.assertThat(findPost).isEqualTo(post);
    }
}