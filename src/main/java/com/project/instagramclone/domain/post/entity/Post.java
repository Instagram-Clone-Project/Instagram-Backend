package com.project.instagramclone.domain.post.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    private Long postId;

    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

//    @OneToMany(mappedBy = "post")
//    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Photo> photos = new ArrayList<>();

//    @OneToMany(mappedBy = "post")
//    private List<Like> Likes = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

//    public void setUser(User user) {
//        user.getPosts.add(this);
//        this.user = user;
//    }


}
