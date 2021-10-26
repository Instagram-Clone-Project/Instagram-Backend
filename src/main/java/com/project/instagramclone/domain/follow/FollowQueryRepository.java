package com.project.instagramclone.domain.follow;

import com.project.instagramclone.web.follow.dto.FollowerListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowQueryRepository extends JpaRepository<Follow,Long> {

    @Query("select count(f) from Follow f where f.follower.userId =:userId")
    public Long getFollower(@Param("userId") Long userId);

    @Query("select count(f) from Follow f where f.following.userId =:userId")
    public Long getFollowing(@Param("userId") Long userId);


    @Query("select f from Follow f where f.follower.userId=:toUserId and f.following.userId=:fromUserId")
    public Optional<Follow> findFollowTableId(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Query("select  f from Follow f where f.follower.userId =:userId")
    public List<Follow> findFollowerList(@Param("userId") Long userId);

    @Query("select  f from Follow f where f.following.userId=:userId")
    public List<Follow> findFollowingList(@Param("userId") Long userId);


    @Query("select f from Follow f where f.following.userId =:fromUserId and f.follower.userId =:toUserId")
    public Optional<Follow> findRelation(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

}
