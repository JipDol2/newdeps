package lotte.newdevps.dto.route.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.route.Route;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.dto.post.request.PostSaveDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RouteSaveDTO {

    private String loginId;
    private String title;
    private String startdate;
    private String enddate;
    private String image;

    private List<String> addressList;
    private List<String> latitude;
    private List<String> longitude;

    @Builder
    public RouteSaveDTO(String loginId, String title, String startdate, String enddate, String image, List<String> addressList, List<String> latitude, List<String> longitude ) {

        this.loginId = loginId;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.image = image;
        this.addressList = addressList;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
