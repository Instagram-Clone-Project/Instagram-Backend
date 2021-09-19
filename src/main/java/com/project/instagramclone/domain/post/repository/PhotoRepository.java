package com.project.instagramclone.domain.post.repository;

import com.project.instagramclone.domain.post.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
