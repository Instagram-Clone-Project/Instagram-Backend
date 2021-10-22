package com.project.instagramclone.domain.comment;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo;
import com.project.instagramclone.web.post.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentQueryRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch NestedComment n where c.post.postId in :postId and c.id = n.comment.id")
    public List<Comment> getCommentsByPostId(@Param("postId") Long postId);


//    @Query("select new com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo(n.content) " +
//            "from NestedComment n where n.comment.id in : commentId")
//    public List<NestedCommentVo> getVo(@Param("commentId") Long commentId);





    @Query("select distinct c from Comment c left outer join fetch c.reply where c.post.postId in :postId")
    public List<Comment> getComments(@Param("postId") Long postId);


    /**
     *
     * 박준순이 만든거임
     */
    @Query("select new com.project.instagramclone.web.post.dto.CommentDto(u.username, c.content) from Comment c join c.user u where c.post.postId = :postId")
    public List<CommentDto> findCommentByPostId(@Param("postId") Long postId);

    /*
    승철 추가
     */
    Long countByPost(Post post);
}
