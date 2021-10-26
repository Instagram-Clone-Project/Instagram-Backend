package com.project.instagramclone.domain.follow;

        import com.project.instagramclone.domain.BaseTimeEntity;
        import com.project.instagramclone.domain.user.User;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;
        import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Follow extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

}
