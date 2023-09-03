package lotte.newdevps.domain.route;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name="ROUTE_INFO")
public class Route {

    public String loginId;
    @Id
    public String routeId;

    public String title;

    public String startdate;

    public String image;

    public String enddate;

}
