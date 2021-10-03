package com.project.instagramclone.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowQueryRepository extends JpaRepository<Follow,Long> {

    @Query("select count(f) from Follow f where f.follwer.userId =:userId")
    public Long getFollow(@Param("userId") Long userId);

    @Query("select count(f) from Follow f where f.following.userId =:userId")
    public Long getFollowing(@Param("userId") Long userId);


    @Query("select f from Follow f where f.follwer.userId=:toUserId and f.following.userId=:fromUserId")
    public Optional<Follow> findFollowTableId(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
}
