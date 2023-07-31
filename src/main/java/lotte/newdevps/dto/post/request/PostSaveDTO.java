package lotte.newdevps.dto.post.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostSaveDTO {

    private String content;
    private String placeTitle;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate dateTime;
    private Double latitude;
    private Double longitude;

    @Builder
    public PostSaveDTO(String content, String placeTitle, LocalDate dateTime, Double latitude, Double longitude) {
        this.content = content;
        this.placeTitle = placeTitle;
        this.dateTime = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Post toEntity(User user, PostSaveDTO dto){
        return Post.builder()
                .content(dto.getContent())
                .placeTitle(dto.getPlaceTitle())
                .dateTime(dto.getDateTime())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .user(user)
                .build();
    }
}
