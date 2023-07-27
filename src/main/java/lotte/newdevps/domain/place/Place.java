package lotte.newdevps.domain.place;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.itineraryPlace.ItineraryPlace;
import lotte.newdevps.domain.recommend.Recommend;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLACE")
public class Place extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    private PlaceCategory category;

    @OneToMany(mappedBy = "place")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<ItineraryPlace> itineraryPlaces = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "recommend_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Recommend recommend;

    @Builder
    public Place(Double latitude, Double longitude, PlaceCategory category) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
    }
}
