package com.project.instagramclone.web.nestedcomment.dto;

import com.project.instagramclone.domain.nestedcomment.NestedComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestedCommentSaveDto {

    private String content;

    public NestedComment toEntity(){
        return NestedComment.builder()
                .content(content)
                .build();
    }
}
