package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.photo.repository.PhotoRepository;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.domain.user.vo.CommentVo;
import com.project.instagramclone.domain.user.vo.HomePostVo;
import com.project.instagramclone.domain.user.vo.PostImageVo;
import com.project.instagramclone.domain.user.vo.ReplyVo;
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

            Long likeCount = likeRepository.countByPost(post);
            Long commentCount = commentRepository.countByPost(post);

            List<Comment> commentList = commentRepository.getComments(post.getPostId());

            for (Comment comment : commentList) {
                commentCount += replyRepository.countByComment(comment);
            }

            List<CommentVo> comments = commentList(post);

            posts.add(HomePostVo.builder()
                    .user(user)
                    .post(post)
                    .images(images)
                    .likeCount(likeCount)
                    .commentCount(commentCount)
                    .comments(comments)
                    .build());
        }

        return HomeResponseDto.builder()
                .posts(posts)
                .build();
    }

    private List<CommentVo> commentList(Post post) {

        List<Comment> commentList = commentRepository.getComments(post.getPostId());
        List<CommentVo> comments = new ArrayList<>();

        for (Comment comment : commentList) {
            Long likeCount = likeRepository.countByComment(comment);
            Long replyCount = replyRepository.countByComment(comment);

            List<ReplyVo> replies = replyList(comment);

            comments.add(CommentVo.builder()
                    .comment(comment)
                    .likeCount(likeCount)
                    .replies(replies)
                    .replyCount(replyCount)
                    .build());
        }

        return comments;
    }

    private List<ReplyVo> replyList(Comment comment) {

        List<NestedComment> replyList = replyRepository.getReplies(comment.getId());
        List<ReplyVo> replies = new ArrayList<>();

        for (NestedComment reply : replyList) {
            Long likeCount = likeRepository.countByReply(reply);

            replies.add(ReplyVo.builder()
                    .reply(reply)
                    .likeCount(likeCount)
                    .build());
        }

        return replies;
    }
}
