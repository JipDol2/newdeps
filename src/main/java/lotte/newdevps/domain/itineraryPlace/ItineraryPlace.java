package lotte.newdevps.domain.itineraryPlace;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.itinerary.Itinerary;
import lotte.newdevps.domain.place.Place;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ITINERARY_PLACE")
public class ItineraryPlace extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Itinerary itinerary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Place place;
}
