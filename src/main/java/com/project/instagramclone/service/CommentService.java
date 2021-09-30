package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.photo.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo;
import com.project.instagramclone.web.post.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Transactional
    public Comment findById(Long id){
        return commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
    }


    @Transactional
    public Comment commentSave(User user, Long postId ,CommentSaveDto commentSaveDto) {


        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다"));

        Comment comment = commentSaveDto.toEntity();
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment commentUpdate(Long comment_id, CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(comment_id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
        comment.update(commentUpdateDto.getContent());
        return comment;
    }

    @Transactional
    public void commentDelete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        commentRepository.delete(comment);
    }


    @Transactional
    public List<CommentVo> getComments(Long postId){
        List<CommentVo> vo = new ArrayList<>();
        List<Comment> test = commentQueryRepository.getComments(postId);
        List<NestedCommentVo> nestedCommentVos = new ArrayList<>();

        for(Comment c : test){
            System.out.println(c.getId()+" "+c.getContent());
            nestedCommentVos = new ArrayList<>();

            for(NestedComment nestedComment : c.getReply()){
                nestedCommentVos.add(new NestedCommentVo(nestedComment.getContent(), nestedComment.getCreatedDate(), nestedComment.getModifiedDate()));
            }
            vo.add(new CommentVo(c,nestedCommentVos));
        }
        return vo;
    }

    /**
     * 박준순이 만든거임
     */
    public List<CommentDto> findAllComments(Long postId) {
        return commentQueryRepository.findCommentByPostId(postId);
    }


}
