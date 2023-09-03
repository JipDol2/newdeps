package lotte.newdevps.dto.route.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.domain.route.Route;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class RouteDTO {

    private String routeId;
    private String title;
    private String startdate;
    private String enddate;
    private String image;


    @Builder
    public RouteDTO(String routeId, String title, String startdate, String enddate, String image) {
        this.routeId = routeId;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.image = image;
    }

    public static List<RouteDTO> toRouteDtoList(List<Route> routes){
        return routes.stream()
                .map(route -> RouteDTO.builder()
                        .routeId(route.routeId)
                        .title(route.title)
                        .startdate(route.startdate)
                        .enddate(route.enddate)
                        .image(route.image)
                        .build())
                .collect(Collectors.toList());
    }
}
