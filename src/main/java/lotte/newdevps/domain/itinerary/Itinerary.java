package lotte.newdevps.domain.itinerary;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.itineraryPlace.ItineraryPlace;
import lotte.newdevps.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ITINERARY")
public class Itinerary extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @OneToMany(mappedBy = "itinerary")
    private List<ItineraryPlace> itineraryPlaces = new ArrayList<>();
}
