package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Photo;
import com.project.instagramclone.web.post.dto.PostTestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p from Photo p where p.post.postId = :postId")
    List<Photo> findPhotoListByPostId(@Param("postId") Long postId);
}
