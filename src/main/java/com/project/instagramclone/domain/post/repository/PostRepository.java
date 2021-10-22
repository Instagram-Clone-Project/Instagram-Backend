package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Post;
import com.project.instagramclone.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.user.userId = :userId order by p.createdDate desc ")
    List<Post> findPostByUsername(@Param("userId") Long userId);

    /*
        수혁 추가
     */

//join  Follow f  where f.following.userId =:userId
//    @Query("select p " +
//            "from Post p " +
//            "join fetch p.user join Follow f " +
//            "on f.following.userId= p.user.userId " +
//            "where f.following.userId =: userId")

//    @Query("select distinct p " +
//            "from Post p join fetch p.user join Follow f on p.user.userId = f.following.userId " +
//            "where f.following.userId =:userId and f.following.userId = p.user.userId " +
//            "order by p.createdDate DESC")

    /*
    승철 추가
     */
    Long countByUser(User user);

    @Query("select p " +
            "from Post p " +
            "join Follow f on p.user.userId = f.follower.userId " +
            "where f.following.userId = :userId " +
            "order by p.createdDate desc ")
    List<Post> findPostHomePage(@Param("userId") Long userId);


}
