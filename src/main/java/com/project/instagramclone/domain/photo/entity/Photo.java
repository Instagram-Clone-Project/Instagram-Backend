package com.project.instagramclone.domain.photo.entity;

import com.project.instagramclone.domain.BaseTimeEntity;
import com.project.instagramclone.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Photo extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long photoId;

    private String fileName;
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
