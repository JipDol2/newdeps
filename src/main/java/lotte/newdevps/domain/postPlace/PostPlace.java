package lotte.newdevps.domain.postPlace;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.place.Place;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST_PLACE")
public class PostPlace extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Place place;
}
