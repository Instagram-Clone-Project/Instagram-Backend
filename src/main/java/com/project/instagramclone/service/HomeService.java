package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.domain.user.vo.HomePostVo;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.home.dto.HomeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentQueryRepository commentRepository;
    private final NestedCommentRepository replyRepository;

    @Transactional
    public HomeResponseDto home(User loginUser) {

        List<Post> postList = postRepository.findPostHomePage(loginUser.getUserId());
        List<HomePostVo> posts = new ArrayList<>();

        for (Post post : postList) {
            User user = userRepository.findById(post.getUser().getUserId())
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

            posts.add(HomePostVo.builder()
                    .user(user)
                    .post(post)
                    .build());
        }

        return HomeResponseDto.builder()
                .posts(posts)
                .build();
    }
}
