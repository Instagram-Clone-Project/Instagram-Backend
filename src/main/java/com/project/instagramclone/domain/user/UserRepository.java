package com.project.instagramclone.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("select u " +
            "from User u " +
            "join Follow f " +
            "on f.following.userId = u.userId " +
            "where f.follower.userId = :userId")
    List<User> findByFollower(@Param("userId") Long userId);

    @Query("select u " +
            "from User u " +
            "join Follow f " +
            "on f.follower.userId = u.userId " +
            "where f.following.userId = :userId")
    List<User> findByFollowing(@Param("userId") Long userId);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);
}