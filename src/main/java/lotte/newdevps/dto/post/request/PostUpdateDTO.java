package lotte.newdevps.dto.post.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.user.User;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PostUpdateDTO {

    private String content;
    private String placeName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate dateTime;
    private Double latitude;
    private Double longitude;

    @Builder
    public PostUpdateDTO(String content, String placeName, LocalDate dateTime, Double latitude, Double longitude) {
        this.content = content;
        this.placeName = placeName;
        this.dateTime = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Post toEntity(User user, PostUpdateDTO dto){
        return Post.builder()
                .content(dto.getContent())
                .placeName(dto.getPlaceName())
                .dateTime(dto.getDateTime())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .user(user)
                .build();
    }
}