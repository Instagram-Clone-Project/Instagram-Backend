package com.project.instagramclone.service;

import com.project.instagramclone.domain.reply.entity.Comment;
import com.project.instagramclone.domain.reply.repository.CommentRepository;
import com.project.instagramclone.web.comment.dto.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;


    public Comment commentSave(CommentSaveDto commentSaveDto) {
        System.out.println("??");
        return commentRepository.save(commentSaveDto.toEntity());
    }
}
