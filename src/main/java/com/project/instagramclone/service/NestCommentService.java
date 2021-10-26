package com.project.instagramclone.service;

/*
 대댓글 관련 서비스
 */

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.Validation;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentSaveDto;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NestCommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final NestedCommentRepository nestedCommentRepository;
    private final Validation validation;
    @Transactional
    public void nestedCommentSave(User user, Long parentCommentId, NestedCommentSaveDto nestedCommentSaveDto){

        Comment parent = commentRepository.findById(parentCommentId).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        NestedComment children = nestedCommentSaveDto.toEntity();
        children.setUser(user);
        parent.setRelation(children);
        nestedCommentRepository.save(children);
    }

    @Transactional
    public void nestedCommentUpdate(User user, Long commentId, NestedCommentUpdateDto nestedCommentUpdateDto){

        NestedComment nestedComment = nestedCommentRepository.findById(commentId).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        validation.userValidationCheck(user.getUserId(), nestedComment.getUser().getUserId());
        nestedComment.update(nestedCommentUpdateDto);
    }

    @Transactional
    public void nestedCommentDelete(User user, Long commentId){

        NestedComment nestedComment = nestedCommentRepository.findById(commentId).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        validation.userValidationCheck(user.getUserId(), nestedComment.getUser().getUserId());
        nestedCommentRepository.delete(nestedComment);

    }
}
