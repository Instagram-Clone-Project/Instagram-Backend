package com.project.instagramclone.domain.nestedcomment;

import com.project.instagramclone.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NestedCommentRepository extends JpaRepository<NestedComment,Long> {

    /*
    승철 추가
     */
    Long countByComment(Comment comment);
}
