package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
}
