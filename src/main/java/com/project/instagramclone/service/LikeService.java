package com.project.instagramclone.service;

import com.project.instagramclone.domain.likes.Likes;
import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.like.dto.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public LikeResponseDto like(Long postId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 기능에서 찾는 글 번호가 없습니다."));

        Likes findLike = likeRepository.findByUserAndPost(user, post)
                .orElse(null);

        if (findLike == null) {
            likeRepository.save(Likes.builder()
                    .user(user)
                    .post(post)
                    .build());
        } else {
            likeRepository.deleteById(findLike.getLikesId());
        }

        return new LikeResponseDto(postId, likeRepository.countByPost(post));
    }
}
