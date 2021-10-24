//package com.project.instagramclone.service;
//
//import com.project.instagramclone.domain.comment.CommentQueryRepository;
//import com.project.instagramclone.domain.post.entity.Post;
//import com.project.instagramclone.domain.post.repository.PostRepository;
//import com.project.instagramclone.domain.comment.Comment;
//import com.project.instagramclone.domain.comment.CommentRepository;
//import com.project.instagramclone.domain.user.User;
//import com.project.instagramclone.domain.user.UserRepository;
//import com.project.instagramclone.web.comment.dto.CommentSaveDto;
//import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
//import com.project.instagramclone.web.auth.dto.SignUpRequestDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//@TestPropertySource("classpath:application_test.properties")
//class CommentServiceTest {
//
//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private NestCommentService nestCommentService;
//
//    @Autowired
//    private CommentQueryRepository commentQueryRepository;
//    @Test
//    public void test(){
//        System.out.println(userRepository.findAll().size());
//    }
//
//    @Test
//    @Transactional
//    public void commentSave() throws Exception{
//        //given
//        Post post = new Post();
//        post.setContent("first post");
//        postRepository.save(post);
//        postRepository.flush();
//
//        CommentSaveDto commentSaveDto = new CommentSaveDto();
//        commentSaveDto.setContent("first comment");
//
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("testsuhyeok@naver.com");
//        signUpRequestDto.setPassword("1234");
//        signUpRequestDto.setUsername("ttestsuhyeok");
//        signUpRequestDto.setName("testsuhyeok");
//
//        User user = signUpRequestDto.toEntity();
//        userRepository.save(user);
//
//        // when
//        Comment comment = commentService.commentSave(user,post.getPostId(),commentSaveDto);
//        Comment saveComment = commentRepository.findById(comment.getId()).get();
//
//        commentRepository.flush();
//        // then
//
//
//        assertThat(comment.getPost().getContent()).isEqualTo("first post");
//        assertThat(comment.getContent()).isEqualTo(saveComment.getContent());
//        assertThat(comment.getId()).isEqualTo(saveComment.getId());
//        assertThat(comment.getUser().getUserId()).isEqualTo(user.getUserId());
//    }
//
//    @Test
//    public void commentUpdate() throws Exception{
//        //given
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
//
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("testsuhyeok@naver.com");
//        signUpRequestDto.setPassword("1234");
//        signUpRequestDto.setUsername("ttestsuhyeok");
//        signUpRequestDto.setName("testsuhyeok");
//
//        User user = signUpRequestDto.toEntity();
//        userRepository.save(user);
//
//
//        // when
//        Comment before = commentService.commentSave(user,post.getPostId(),commentSaveDto);
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
//        Post post = new Post();
//        post.setContent("first post");
//        postRepository.save(post);
//        postRepository.flush();
//
//        CommentSaveDto commentSaveDto = new CommentSaveDto();
//        commentSaveDto.setContent("first comment");
//
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("testsuhyeok@naver.com");
//        signUpRequestDto.setPassword("1234");
//        signUpRequestDto.setUsername("testtestsuhyeok");
//        signUpRequestDto.setName("testsuhyeok");
//
//        User user = signUpRequestDto.toEntity();
//        userRepository.save(user);
//
//
//        // when
//        Comment comment = commentService.commentSave(user,post.getPostId(),commentSaveDto);
//        commentRepository.delete(comment);
//        List<Comment> list = commentRepository.findAll();
//        // then
//        assertThat(list.size()).isEqualTo(0);
//    }
//
//
////    @Test
////    @Transactional
////    public void nestedCommentSave() throws Exception{
////        //given
////        CommentSaveDto commentParent = new CommentSaveDto();
////        commentParent.setContent("부모 댓글");
////
////        SignUpRequestDto parentUser = new SignUpRequestDto();
////        parentUser.setEmail("parent");
////        parentUser.setPassword("1234");
////        parentUser.setUsername("parent");
////        parentUser.setName("parent");
////
////        User user1 = parentUser.toEntity();
////        userRepository.save(user1);
////
////
////        CommentSaveDto commentChildren = new CommentSaveDto();
////        commentChildren.setContent("자식 댓글 (대댓글)");
////
////        SignUpRequestDto childUser = new SignUpRequestDto();
////        childUser.setEmail("child");
////        childUser.setPassword("1234");
////        childUser.setUsername("child");
////        childUser.setName("child");
////
////        User user2 = childUser.toEntity();
////        userRepository.save(user2);
////
////        // when
////        Comment parent = commentService.commentSave(user1,commentParent);
////        Comment children = commentService.nestedCommentSave(parent.getId(),commentChildren);
////        commentRepository.flush();
////
////        Comment parentToChild = parent.getChildren().get(0);
////        Comment childToParent = children.getParent();
////        // then
////
////
////        assertThat(parentToChild.getId()).isEqualTo(children.getId());
////        assertThat(parentToChild.getContent()).isEqualTo(children.getContent());
////
////        assertThat(childToParent.getId()).isEqualTo(parent.getId());
////        assertThat(childToParent.getContent()).isEqualTo(parent.getContent());
////    }
//
//
//    @Test
//    @Transactional
//    public void 게시글댓글조회() throws Exception{
//        User user = 회원가입();
//        userRepository.save(user);
//        userRepository.flush();
//
//        // 첫번째 게시글
//        Post post1 = new Post();
//        post1.setContent("first post");
//        postRepository.save(post1);
//
//        // 두번째 게시글
//        Post post2 = new Post();
//        post2.setContent("second post");
//        postRepository.save(post2);
//
//        postRepository.flush();
//        // 첫번째 게시글의 댓글1
//        CommentSaveDto commentSaveDto1 = new CommentSaveDto();
//        commentSaveDto1.setContent("first - first comment");
//        commentService.commentSave(user,post1.getPostId(),commentSaveDto1);
//
//        // 첫번째 게시글의 댓글2
//        CommentSaveDto commentSaveDto2 = new CommentSaveDto();
//        commentSaveDto2.setContent("first - second comment");
//        commentService.commentSave(user,post1.getPostId(),commentSaveDto2);
//
//        // 첫번째 게시글의 댓글3
//        CommentSaveDto commentSaveDto3 = new CommentSaveDto();
//        commentSaveDto3.setContent("first - third comment");
//        commentService.commentSave(user,post1.getPostId(),commentSaveDto3);
//
//
//        // 두번째 게시글의 댓글1
//        CommentSaveDto comment1 = new CommentSaveDto();
//        comment1.setContent("second - first comment");
//        commentService.commentSave(user,post2.getPostId(),comment1);
//
//        // 첫번째 게시글의 댓글2
//        CommentSaveDto comment2 = new CommentSaveDto();
//        comment2.setContent("second - second comment");
//        commentService.commentSave(user,post2.getPostId(),comment2);
//
//        commentRepository.flush();
//
//
//        // 첫번째 게시글의 대댓글
//
//        Comment tmp = commentRepository.findById(1L).get();
//
//        CommentSaveDto recomment1 = new CommentSaveDto();
//        recomment1.setContent("first-first reply comment");
//        nestCommentService.nestedCommentSave(user, post1.getPostId(),tmp.getId(), recomment1);
//
//
//        List<Comment> result1 = commentQueryRepository.getCommentsByPostId(post1.getPostId());
//        List<Comment> result2 = commentQueryRepository.getCommentsByPostId(post2.getPostId());
//
//
//
//        assertThat(result1.size()).isEqualTo(4);
//        assertThat(result2.size()).isEqualTo(2);
//    }
//
//    public User 회원가입(){
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("testsuhyeok@naver.com");
//        signUpRequestDto.setPassword("1234");
//        signUpRequestDto.setUsername("testtestsuhyeok");
//        signUpRequestDto.setName("testsuhyeok");
//        return signUpRequestDto.toEntity();
//    }
//}