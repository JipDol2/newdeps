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
    private String address;
    private String categoryName;
    private Double starRating;

    @Builder
    public PostUpdateDTO(String content, String placeName, LocalDate dateTime, Double latitude, Double longitude, String address, String categoryName, Double starRating) {
        this.content = content;
        this.placeName = placeName;
        this.dateTime = dateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.categoryName = categoryName;
        this.starRating = starRating;
    }

    public static Post toEntity(User user, PostUpdateDTO dto) {
        return Post.builder()
                .content(dto.getContent())
                .placeName(dto.getPlaceName())
                .dateTime(dto.getDateTime())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .address(dto.getAddress())
                .categoryName(dto.getCategoryName())
                .starRating(dto.getStarRating())
                .user(user)
                .build();
    }
}
