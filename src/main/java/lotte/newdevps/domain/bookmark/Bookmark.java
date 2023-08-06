package lotte.newdevps.domain.bookmark;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.placeBookmark.PlaceBookmark;
import lotte.newdevps.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    @OneToMany(mappedBy = "bookmark",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PlaceBookmark> placeBookmarks = new ArrayList<>();

    @Builder
    public Bookmark(BookmarkType bookmarkType, User user) {
        this.bookmarkType = bookmarkType;
        if(this.user != null){
            this.user.getBookmarks().remove(this);
        }
        this.user = user;
        user.getBookmarks().add(this);
    }

}
