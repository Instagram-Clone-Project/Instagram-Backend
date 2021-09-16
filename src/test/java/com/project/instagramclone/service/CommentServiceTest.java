package com.project.instagramclone.service;

import com.project.instagramclone.domain.reply.entity.Comment;
import com.project.instagramclone.domain.reply.repository.CommentRepository;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @Test
    public void test(){

    }
    // 21-09-16 : 팀원들이 user post 완성하면 그거 넣어서 테스트해보기
    @Test
    public void commentSave() throws Exception{
        //given

        CommentSaveDto commentSaveDto = new CommentSaveDto();
        commentSaveDto.setContent("첫번째 댓글");

        // when
        Comment comment = commentService.commentSave(commentSaveDto);
        Comment saveComment = commentRepository.findById(comment.getId()).get();

        // then

        assertThat(comment.getContent()).isEqualTo(saveComment.getContent());
        assertThat(comment.getId()).isEqualTo(saveComment.getId());
    }

    @Test
    public void commentUpdate() throws Exception{
        //given
        CommentSaveDto commentSaveDto = new CommentSaveDto();
        commentSaveDto.setContent("수정 전");

        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setContent("수정 후");
        // when

        Comment before = commentService.commentSave(commentSaveDto);
        System.out.println(before.getId());
        Comment after = commentService.findById(before.getId());

        commentService.commentUpdate(before.getId(),commentUpdateDto);
        commentRepository.flush();

        Comment comment = commentService.findById(before.getId());
        // then

        assertThat(comment.getContent()).isEqualTo(commentUpdateDto.getContent());
    }
}