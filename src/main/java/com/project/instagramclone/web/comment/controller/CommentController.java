package com.project.instagramclone.web.comment.controller;

import com.project.instagramclone.domain.reply.entity.Comment;
import com.project.instagramclone.service.CommentService;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comment")
    public Comment commentSave(@RequestBody CommentSaveDto commentSaveDto){
        commentSaveDto.toEntity();
        return commentService.commentSave(commentSaveDto);
    }
}
