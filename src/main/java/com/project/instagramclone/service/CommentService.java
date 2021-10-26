package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.Validation;
import com.project.instagramclone.web.comment.dto.CommentGetDto;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo;
import com.project.instagramclone.web.post.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentQueryRepository commentQueryRepository;
    private final Validation validation;
    @Transactional
    public Comment findById(Long id){
        return commentRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }


    @Transactional
    public Comment commentSave(User user, Long postId ,CommentSaveDto commentSaveDto) {


        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));

        Comment comment = commentSaveDto.toEntity();
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    @Transactional
    public void commentUpdate(Long comment_id, CommentUpdateDto commentUpdateDto, User user){
        Comment comment = commentRepository.findById(comment_id).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        validation.userValidationCheck(comment.getUser().getUserId(), user.getUserId());
        comment.update(commentUpdateDto.getContent());
    }

    @Transactional
    public void commentDelete(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        validation.userValidationCheck(comment.getUser().getUserId(), user.getUserId());
        commentRepository.delete(comment);
    }
}
