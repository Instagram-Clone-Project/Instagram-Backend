package com.project.instagramclone.web.comment.dto;

import com.project.instagramclone.domain.comment.Comment;
//import com.project.instagramclone.domain.user.vo.CommentUserVo;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    //private CommentUserVo commentUserVo;

    private String content;
    private List<NestedCommentVo> reply;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public CommentVo(Comment comment, List<NestedCommentVo> vo){
        //this.commentUserVo = commentUserVo;
        this.content = comment.getContent();
        this.reply = vo;
        this.createDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
    }
}
