package lotte.newdevps.domain.bookmark;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.user.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BOOKMARK")
public class Bookmark extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BookmarkType bookmarkType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Place place;
}
