package lotte.newdevps.domain.place;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.itineraryPlace.ItineraryPlace;
import lotte.newdevps.domain.placeBookmark.PlaceBookmark;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLACE")
public class Place extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    private PlaceCategory category;

    private String placeName;

    @OneToMany(mappedBy = "place")
    private List<PlaceBookmark> placeBookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    private List<ItineraryPlace> itineraryPlaces = new ArrayList<>();

    @Builder
    public Place(Double latitude, Double longitude, PlaceCategory category, String placeName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.placeName = placeName;
    }
}
