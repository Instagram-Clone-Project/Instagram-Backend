package com.project.instagramclone.domain.comment.repository;

import com.project.instagramclone.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    @Query("select c from Comment c where c.post.postId =:postId")
    public List<Comment> getCommentsByPostId(@Param("postId") Long postId);
}
