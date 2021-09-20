package com.project.instagramclone.domain.comment.repository;

import com.project.instagramclone.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
