package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.photo.repository.PhotoRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.domain.user.vo.HomePostVo;
import com.project.instagramclone.domain.user.vo.PostImageVo;
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
    private final PhotoRepository photoRepository;
    private final CommentQueryRepository commentRepository;
    private final NestedCommentRepository replyRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public HomeResponseDto home(User loginUser) {

        List<Post> postList = postRepository.findPostHomePage(loginUser.getUserId());
        List<HomePostVo> posts = new ArrayList<>();

        for (Post post : postList) {
            User user = userRepository.findById(post.getUser().getUserId())
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

            List<Photo> imageList = photoRepository.findPhotoListByPostId(post.getPostId());
            List<PostImageVo> images = new ArrayList<>();

            for (Photo image : imageList) {
                images.add(PostImageVo.builder()
                        .image(image)
                        .build());
            }

            Long postLikeCount = likeRepository.countByPost(post);
            Long postCommentCount = commentRepository.countByPost(post);

            List<Comment> commentList = commentRepository.getComments(post.getPostId());

            for (Comment comment : commentList) {
                postCommentCount += replyRepository.countByComment(comment);
            }

            posts.add(HomePostVo.builder()
                    .user(user)
                    .post(post)
                    .images(images)
                    .likeCount(postLikeCount)
                    .commentCount(postCommentCount)
                    .build());
        }

        return HomeResponseDto.builder()
                .posts(posts)
                .build();
    }
}
