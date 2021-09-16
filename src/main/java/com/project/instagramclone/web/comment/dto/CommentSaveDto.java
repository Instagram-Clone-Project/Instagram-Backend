package com.project.instagramclone.web.comment.dto;

import com.project.instagramclone.domain.reply.entity.Comment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {

    private String content;

//    private Post post;
//    private User user;


    public Comment toEntity(){
        return Comment.builder()
                .content(content)
//                .post(post)
//                .user(user)
                .build();
    }
}
