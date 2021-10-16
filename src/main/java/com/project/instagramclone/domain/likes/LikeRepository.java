package com.project.instagramclone.domain.likes;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    //해당 Post 에 User 가 누른 좋아요 조회
    Optional<Likes> findByUserAndPost(User user, Post post);

    Long countByPost(Post post);

    Long deleteByUserAndPost(User user, Post post);
}
