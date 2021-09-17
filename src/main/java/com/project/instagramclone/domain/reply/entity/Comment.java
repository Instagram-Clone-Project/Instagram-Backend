package com.project.instagramclone.domain.reply.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.web.comment.dto.CommentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")   // 양방향 무한루프방지
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 나중에 Validation 적용해보기기
    @Column(nullable = false)
    private String content;


//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "post_id")
//    private Post post;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    @Builder.Default    // 값이 초기화 돼 있는 경우 빌더로 의해서 변경될수 있다고 명시
    private List<Comment> children = new ArrayList<>();

    public void setRelationComment(Comment parent, Comment children){
        parent.setChildren(children);
        children.setParent(parent);
    }

    public void setParent(Comment comment){
        this.parent = comment;
    }

    public void setChildren(Comment children){
        this.children.add(children);
    }


    public void update(String content){
        this.content =  content;
    }

}
