package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.user.userId = :userId")
    List<Post> findPostByUsername(@Param("userId") Long userId);
}
