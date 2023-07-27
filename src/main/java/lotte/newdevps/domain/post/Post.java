package lotte.newdevps.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.comment.Comment;
import lotte.newdevps.domain.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long viewCount;

    private String placeTitle;

    private Double latitude;

    private Double longitude;

    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String content, String placeTitle, Double latitude, Double longitude, LocalDate dateTime, User user) {
        this.content = content;
        this.viewCount = 0L;
        this.placeTitle = placeTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.user = user;
    }
}
