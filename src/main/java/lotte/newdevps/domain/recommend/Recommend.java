package lotte.newdevps.domain.recommend;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.place.Place;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RECOMMEND")
public class Recommend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Place place;
}
