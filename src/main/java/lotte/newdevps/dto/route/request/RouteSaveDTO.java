package lotte.newdevps.dto.route.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RouteSaveDTO {

    private String loginId;
    private String routeId;
    private String title;
    private String startdate;
    private String enddate;
    private String image;

    private List<String> addressList;
    private List<String> latitude;
    private List<String> longitude;

}
