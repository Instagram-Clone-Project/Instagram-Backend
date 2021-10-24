package com.project.instagramclone.service;

import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.comment.CommentQueryRepository;
import com.project.instagramclone.domain.follow.FollowQueryRepository;
import com.project.instagramclone.domain.likes.LikeRepository;
import com.project.instagramclone.domain.nestedcomment.NestedCommentRepository;
import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.domain.user.vo.*;
import com.project.instagramclone.exception.CustomException;
import com.project.instagramclone.exception.ErrorCode;
import com.project.instagramclone.web.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentQueryRepository commentRepository;
    private final NestedCommentRepository replyRepository;
    private final FollowQueryRepository followRepository;
    private final LikeRepository likeRepository;

    private final PasswordEncoder passwordEncoder;
    private final FileUploadService fileUploadService;

    @Transactional
    public User update(Long userId, EditRequestDto editRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateUser(editRequestDto);

        return user;
    }

    @Transactional
    public EditResponseDto info(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new EditResponseDto(user);
    }

    @Transactional
    public User uploadProfileImage(Long userId, MultipartFile profileImageFile) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        String profileImageUrl = fileUploadService.uploadImage(profileImageFile);

        user.updateProfileImage(profileImageUrl);

        return user;
    }

    @Transactional
    public User deleteProfileImage(Long userId, String profileImageUrl) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateProfileImage(null);

        return user;
    }

    @Transactional
    public User updatePassword(Long userId, PasswordChangeRequestDto passwordChangeRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //첫 번째 조건문: 현재 비밀번호 일치 여부
        //두 번째 조건문: 새 비밀번호와 비밀번호 확인 일치 여부
        if (!passwordEncoder.matches(passwordChangeRequestDto.getOldPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_OLD_PASSWORD);
        } if (!passwordChangeRequestDto.getNewPassword().equals(passwordChangeRequestDto.getConfirmPassword())) {
            throw new CustomException(ErrorCode.MISMATCH_CONFIRM_PASSWORD);
        }

        user.updatePassword(passwordEncoder.encode(passwordChangeRequestDto.getNewPassword()));

        return user;
    }

    @Transactional
    public ProfileResponseDto mainProfile(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Long postCount = postRepository.countByUser(user);
        Long followerCount = followRepository.getFollower(user.getUserId());
        Long followingCount = followRepository.getFollowing(user.getUserId());

        List<Post> postList = postRepository.findPostByUsername(user.getUserId());
        List<ProfilePostVo> posts = new ArrayList<>();

        for (Post post : postList) {
            Long likeCount = likeRepository.countByPost(post);
            Long commentCount = commentRepository.countByPost(post);

            List<Comment> commentList = commentRepository.getComments(post.getPostId());

            for (Comment comment : commentList) {
                commentCount += replyRepository.countByComment(comment);
            }

            List<PostImageVo> images = new ArrayList<>();

            for (Photo photo : post.getPhotos()) {
                images.add(PostImageVo.builder()
                        .image(photo)
                        .build());
            }

            posts.add(ProfilePostVo.builder()
                    .post(post)
                    .likeCount(likeCount)
                    .commentCount(commentCount)
                    .images(images)
                    .build());
        }

        return ProfileResponseDto.builder()
                .user(ProfileUserVo.builder().user(user).build())
                .postCount(postCount)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .posts(posts)
                .build();
    }

    @Transactional
    public FollowingsResponseDto getFollowings(String username, User loginUser) {

        User searchUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<User> followingList = userRepository.findByFollowing(searchUser.getUserId());
        List<ProfileFollowingVo> followings = new ArrayList<>();

        for (User following : followingList) {
            boolean followRelation = false;

            if (followRepository.findRelation(loginUser.getUserId(), following.getUserId()).isPresent()) {
                followRelation = true;
            }

            followings.add(ProfileFollowingVo.builder()
                    .user(following)
                    .followRelation(followRelation)
                    .build());
        }

        return FollowingsResponseDto.builder()
                .followings(followings)
                .build();
    }

    @Transactional
    public FollowersResponseDto getFollowers(String username, User loginUser) {

        User searchUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<User> followerList = userRepository.findByFollower(searchUser.getUserId());
        List<ProfileFollowerVo> followers = new ArrayList<>();

        for (User follower : followerList) {
            boolean followRelation = false;

            if (followRepository.findRelation(loginUser.getUserId(), follower.getUserId()).isPresent()) {
                followRelation = true;
            }

            followers.add(ProfileFollowerVo.builder()
                    .user(follower)
                    .followRelation(followRelation)
                    .build());
        }

        return FollowersResponseDto.builder()
                .followers(followers)
                .build();
    }
}
