package com.project.instagramclone.domain.post.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Photo {

    @Id @GeneratedValue
    private Long photoId;

    private String fileName;
    private String encFileName;
    private String route;
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
        post.getPhotos().add(this);
    }

}
