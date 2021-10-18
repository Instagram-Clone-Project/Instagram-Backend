package com.project.instagramclone.web.post.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentDto {

    @ApiModelProperty(example = "댓글작성자")
    private String username;
    
    @ApiModelProperty(example = "댓글")
    private String content;

    public CommentDto(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
