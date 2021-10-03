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
    @Transactional
    public void nestedCommentSave(User user, Long postId, Long parentCommentId, NestedCommentSaveDto nestedCommentSaveDto){

        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다"));
        Comment parent = commentRepository.findById(parentCommentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        NestedComment children = nestedCommentSaveDto.toEntity();

        parent.setRelation(children);
        nestedCommentRepository.save(children);

    }

    @Transactional
    public void nestedCommentUpdate(Long commentId, NestedCommentUpdateDto nestedCommentUpdateDto){

        NestedComment nestedComment = nestedCommentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        nestedComment.update(nestedCommentUpdateDto);
    }

    @Transactional
    public void nestedCommentDelete(Long commentId){

        NestedComment comment = nestedCommentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
        nestedCommentRepository.delete(comment);

    }
}
