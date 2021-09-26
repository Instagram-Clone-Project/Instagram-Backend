package com.project.instagramclone.service;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.post.repository.PostRepository;
import com.project.instagramclone.domain.comment.entity.Comment;
import com.project.instagramclone.domain.comment.repository.CommentRepository;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.user.UserRepository;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import com.project.instagramclone.web.user.dto.SignUpRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void test(){

    }

    @Test
    @Transactional
    public void commentSave() throws Exception{
        //given
        Post post = new Post();
        post.setContent("first post");
        postRepository.save(post);
        postRepository.flush();

        CommentSaveDto commentSaveDto = new CommentSaveDto();
        commentSaveDto.setContent("first comment");

        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("testsuhyeok@naver.com");
        signUpRequestDto.setPassword("1234");
        signUpRequestDto.setUsername("ttestsuhyeok");
        signUpRequestDto.setName("testsuhyeok");

        User user = signUpRequestDto.toEntity();
        userRepository.save(user);

        // when
        Comment comment = commentService.commentSave(user,post.getPostId(),commentSaveDto);
        Comment saveComment = commentRepository.findById(comment.getId()).get();

        commentRepository.flush();
        // then


        assertThat(comment.getPost().getContent()).isEqualTo("first post");
        assertThat(comment.getContent()).isEqualTo(saveComment.getContent());
        assertThat(comment.getId()).isEqualTo(saveComment.getId());
        assertThat(comment.getUser().getUserId()).isEqualTo(user.getUserId());
    }

//    @Test
//    public void commentUpdate() throws Exception{
//        //given
//
//
//        Post post = new Post();
//        post.setContent("first post");
//        postRepository.save(post);
//        postRepository.flush();
//
//        CommentSaveDto commentSaveDto = new CommentSaveDto();
//        commentSaveDto.setContent("before");
//
//        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
//        commentUpdateDto.setContent("after");
//        // when
//
//        Comment before = commentService.commentSave(post.getPostId(),commentSaveDto);
//        System.out.println(before.getId());
//        Comment after = commentService.findById(before.getId());
//
//        commentService.commentUpdate(before.getId(),commentUpdateDto);
//        commentRepository.flush();
//
//        Comment comment = commentService.findById(before.getId());
//        // then
//
//        assertThat(comment.getContent()).isEqualTo(commentUpdateDto.getContent());
//    }
//
//    @Test
//    public void commentDelete() throws Exception{
//        //given
//        CommentSaveDto commentSaveDto = new CommentSaveDto();
//        commentSaveDto.setContent("첫번째 댓글");
//
//        // when
//        //Comment comment = commentService.commentSave(commentSaveDto);
//        commentRepository.delete(comment);
//        List<Comment> list = commentRepository.findAll();
//        // then
//
//
//        assertThat(list.size()).isEqualTo(0);
//    }


//    @Test
//    @Transactional
//    public void nestedCommentSave() throws Exception{
//        //given
//        CommentSaveDto commentParent = new CommentSaveDto();
//        commentParent.setContent("부모 댓글");
//
//
//        CommentSaveDto commentChildren = new CommentSaveDto();
//        commentChildren.setContent("자식 댓글 (대댓글)");
//
//
//        // when
//        Comment parent = commentService.commentSave(commentParent);
//        Comment children = commentService.nestedCommentSave(parent.getId(),commentChildren);
//        commentRepository.flush();
//
//        Comment parentToChild = parent.getChildren().get(0);
//        Comment childToParent = children.getParent();
//        // then
//
//
//        assertThat(parentToChild.getId()).isEqualTo(children.getId());
//        assertThat(parentToChild.getContent()).isEqualTo(children.getContent());
//
//        assertThat(childToParent.getId()).isEqualTo(parent.getId());
//        assertThat(childToParent.getContent()).isEqualTo(parent.getContent());
//    }
}