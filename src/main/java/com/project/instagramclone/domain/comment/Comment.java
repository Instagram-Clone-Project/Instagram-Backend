package com.project.instagramclone.domain.comment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.nestedcomment.NestedComment;
import com.project.instagramclone.domain.user.User;
import com.project.instagramclone.domain.post.entity.Post;
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
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 나중에 Validation 적용해보기기
    @Column(nullable = false)
    private String content;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "post_id")
    private Post post;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    @Builder.Default
    private List<NestedComment> reply = new ArrayList<>();



    public void setRelation(NestedComment nestedComment){
        this.reply.add(nestedComment);
        nestedComment.setComment(this);
    }

    public void setPost(Post post){
        this.post = post;
        post.getComments().add(this);
    }

    public void setUser(User user){
        this.user = user;
    }



    public void update(String content){
        this.content =  content;
    }

}
