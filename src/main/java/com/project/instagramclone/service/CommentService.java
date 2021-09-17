package com.project.instagramclone.service;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.reply.entity.Comment;
import com.project.instagramclone.domain.reply.repository.CommentRepository;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
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
    public Comment commentSave(CommentSaveDto commentSaveDto) {

        return commentRepository.save(commentSaveDto.toEntity());
    }

    @Transactional
    // 권한에 관련된 처리? 해당된 회원만 글을 수정할 수 있게 해야함 후에 추가하기
    public Comment commentUpdate(Long comment_id, CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(comment_id).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));
        comment.update(commentUpdateDto.getContent());
        return comment;
    }

    @Transactional
    public void commetDelete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        commentRepository.delete(comment);
    }

    @Transactional
    public Comment nestedCommentSave(Long parentCommentId, CommentSaveDto comment){

        Comment parent = commentRepository.findById(parentCommentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글이 없습니다"));

        Comment children = commentRepository.save(comment.toEntity());

        parent.setRelationComment(parent,children);

        commentRepository.save(children);

        return children;
    }

}
