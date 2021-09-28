package com.project.instagramclone.domain.comment;

import com.project.instagramclone.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {



}
