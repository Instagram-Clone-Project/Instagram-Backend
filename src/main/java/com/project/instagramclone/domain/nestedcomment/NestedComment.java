package com.project.instagramclone.domain.nestedcomment;

import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.web.nestedcomment.dto.NestedCommentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NestedComment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void setComment(Comment comment){
        this.comment = comment;
    }

    public void update(NestedCommentUpdateDto updateDto){
        this.content = updateDto.getContent();
    }
}
