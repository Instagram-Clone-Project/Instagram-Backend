package com.project.instagramclone.domain.likes;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.web.activity.dto.ActivityListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    //해당 Post 에 User 가 누른 좋아요 조회
    Optional<Likes> findByUserAndPost(User user, Post post);
    Optional<Likes> findByUserAndComment(User user, Comment comment);
    Optional<Likes> findByUserAndReply(User user, NestedComment reply);

    Long countByPost(Post post);
    Long countByComment(Comment comment);
    Long countByReply(NestedComment reply);

//    @Query("select count(l) " +
//            "from Likes l " +
//            "join Comment c on l.comment.id = c.id " +
//            "where c.id = :commentId")
//    Long countByComment(@Param("commentId") Long commentId);
//
//    @Query("select count(l) " +
//            "from Likes l " +
//            "join NestedComment r on l.reply.id = r.id " +
//            "where r.id = :replyId")
//    Long countByReply(@Param("replyId") Long replyId);

    Long deleteByUserAndPost(User user, Post post);


    @Query("select l from Likes l join fetch l.post p join fetch l.user u join fetch p.photos where p.user.userId in :userId")
    List<Likes> getPostLike(@Param("userId") Long userId);

    @Query("select l from Likes l join fetch l.comment c join fetch l.user u where c.user.userId in :userId")
    List<Likes> getCommentLike(@Param("userId") Long userId);
}
