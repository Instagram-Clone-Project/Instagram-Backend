package com.project.instagramclone.domain.nestedcomment;

import com.project.instagramclone.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NestedCommentRepository extends JpaRepository<NestedComment,Long> {

    /*
    승철 추가
     */
    Long countByComment(Comment comment);

    @Query("select r " +
            "from NestedComment r " +
            "join Comment c on r.comment.id = c.id " +
            "where c.id = :commentId")
    List<NestedComment> getReplies(@Param("commentId") Long commentId);
}
