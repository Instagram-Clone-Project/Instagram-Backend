package com.project.instagramclone.service;

/*
 대댓글 관련 서비스
 */

import com.project.instagramclone.domain.comment.entity.Comment;
import com.project.instagramclone.domain.comment.repository.CommentRepository;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NestCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void nestedCommentSave(User user, Long parentCommentId, CommentSaveDto comment){

        Comment parent = commentRepository.findById(parentCommentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
        Comment children = commentRepository.save(comment.toEntity());
        children.setUser(user);
        parent.setRelationComment(parent,children);

        commentRepository.save(children);
    }

    @Transactional
    public void nestedCommentUpdate(Long commentId, CommentUpdateDto commentUpdateDto){

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        comment.update(commentUpdateDto.getContent());
    }

    @Transactional
    public void nestedCommetDelete(Long commentId){

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
        commentRepository.delete(comment);

    }
}
