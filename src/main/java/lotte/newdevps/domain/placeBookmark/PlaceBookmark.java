package lotte.newdevps.domain.placeBookmark;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.place.Place;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceBookmark {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookmark_id")
    private Bookmark bookmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public PlaceBookmark(Bookmark bookmark, Place place) {
        this.bookmark = bookmark;
        this.place = place;
    }

    public void setBookmark(Bookmark bookmark){
        if(this.bookmark!=null){
            this.bookmark.getPlaceBookmarks().remove(this);
        }
        this.bookmark = bookmark;
        bookmark.getPlaceBookmarks().add(this);
    }
}
