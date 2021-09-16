package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Long save(Post post) {
        em.persist(post);
        return post.getPostId();
    }

    public void remove(Post post) {
        em.remove(post);
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select  p from Post p", Post.class)
                .getResultList();
    }


}
