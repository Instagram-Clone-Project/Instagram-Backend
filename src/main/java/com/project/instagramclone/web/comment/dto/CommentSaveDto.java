package com.project.instagramclone.web.comment.dto;

import com.project.instagramclone.domain.comment.entity.Comment;
import com.project.instagramclone.domain.post.entity.Post;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {

    private String content;


    public Comment toEntity(){
        return Comment.builder()
                .content(content)
                .build();
    }
}
