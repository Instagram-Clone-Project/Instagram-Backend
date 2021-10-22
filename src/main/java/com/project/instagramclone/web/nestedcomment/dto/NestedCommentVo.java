package com.project.instagramclone.web.nestedcomment.dto;

//import com.project.instagramclone.domain.user.vo.CommentUserVo;
import com.project.instagramclone.web.comment.dto.CommentVo;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NestedCommentVo {

    //private CommentUserVo commentUserVo;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
}
