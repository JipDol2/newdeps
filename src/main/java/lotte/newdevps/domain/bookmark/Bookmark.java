package lotte.newdevps.domain.bookmark;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.user.User;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Place place;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    @Builder
    public Bookmark(BookmarkType bookmarkType, User user, Place place, Post post) {
        this.bookmarkType = bookmarkType;
        if(this.user != null){
            this.user.getBookmarks().remove(this);
        }

        this.user = user;
        user.getBookmarks().add(this);

        if(place !=null) {
            this.place = place;
            place.setBookmark(this);
        }

        if(post!=null) {
            this.post = post;
            post.setBookmark(this);
        }
    }
}
