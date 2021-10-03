package com.project.instagramclone.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow,Long> {


}
