package com.project.instagramclone.service;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.comment.entity.Comment;
import com.project.instagramclone.domain.comment.repository.CommentRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.security.UserDetailsImpl;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

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





}