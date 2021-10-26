package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentRepository;
import com.project.instagramclone.domain.likes.Likes;
import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.activity.dto.ActivityDto;
import com.project.instagramclone.web.activity.dto.ActivityListDto;
import com.project.instagramclone.web.activity.dto.AlarmType;
import com.project.instagramclone.web.like.dto.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NestedCommentRepository replyRepository;

    @Transactional
    public LikeResponseDto likePost(Long postId, User loginUser) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Likes like = likeRepository.findByUserAndPost(loginUser, post)
                .orElse(null);

        if (like == null) {
            likeRepository.save(Likes.builder()
                    .user(loginUser)
                    .post(post)
                    .build());
        } else {
            likeRepository.deleteById(like.getLikesId());
        }

        return LikeResponseDto.builder()
                .id(postId)
                .totalLike(likeRepository.countByPost(post))
                .build();
    }

    @Transactional
    public LikeResponseDto likeComment(Long commentId, User loginUser) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        Likes like = likeRepository.findByUserAndComment(loginUser, comment)
                .orElse(null);

        if (like == null) {
            likeRepository.save(Likes.builder()
                    .user(loginUser)
                    .comment(comment)
                    .build());
        } else {
            likeRepository.deleteById(like.getLikesId());
        }

        return LikeResponseDto.builder()
                .id(commentId)
                .totalLike(likeRepository.countByComment(comment))
                .build();
    }

    @Transactional
    public LikeResponseDto likeReply(Long replyId, User loginUser) {

        NestedComment reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        Likes like = likeRepository.findByUserAndReply(loginUser, reply)
                .orElse(null);

        if (like == null) {
            likeRepository.save(Likes.builder()
                    .user(loginUser)
                    .reply(reply)
                    .build());
        } else {
            likeRepository.deleteById(like.getLikesId());
        }

        return LikeResponseDto.builder()
                .id(replyId)
                .totalLike(likeRepository.countByReply(reply))
                .build();
    }
}
