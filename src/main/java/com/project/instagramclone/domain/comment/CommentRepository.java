package com.project.instagramclone.domain.comment;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.web.post.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
