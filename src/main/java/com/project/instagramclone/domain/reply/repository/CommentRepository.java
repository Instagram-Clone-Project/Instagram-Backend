package com.project.instagramclone.domain.reply.repository;

import com.project.instagramclone.domain.reply.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
