package com.project.instagramclone.web.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetDto {

    private ArrayList<CommentVo> commentVos = new ArrayList<>();
}
