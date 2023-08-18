package lotte.newdevps.domain.route;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@Table(name="ROUTE_INFO")
public class Route {

    public String loginId;
    @Id
    public int routeId;

    public String title;

    public String startdate;

    public String image;

    public String enddate;

    @Builder
    public Route(String loginId, int routeId, String title, String startdate, String enddate, String image){
        this.loginId = loginId;
        this.routeId = routeId;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.image = image;
    }
}
