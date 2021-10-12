package com.project.instagramclone.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.comment.Comment;
import com.project.instagramclone.domain.likes.Likes;
import com.project.instagramclone.domain.photo.entity.Photo;
import com.project.instagramclone.domain.user.User;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long postId;

    private String content;
//    private LocalDateTime createdDate;
//    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Likes> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setMember(User user) {
//        user.getPosts.add(this);
        this.user = user;
    }


}
