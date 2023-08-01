package lotte.newdevps.dto.post.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.domain.post.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private String content;
    private Long viewCount;
    private String placeTitle;
    private Double latitude;
    private Double longitude;
    private LocalDate dateTime;

    @Builder
    public PostDTO(String content, Long viewCount, String placeTitle, Double latitude, Double longitude, LocalDate dateTime) {
        this.content = content;
        this.viewCount = viewCount;
        this.placeTitle = placeTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public static PostDTO toDto(Post post) {
        return PostDTO.builder()
                .content(post.getContent())
                .placeTitle(post.getPlaceName())
                .dateTime(post.getDateTime())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .build();
    }

    public static List<PostDTO> toDtoList(List<Post> posts) {
        return posts.stream()
                .map(p -> PostDTO.builder()
                        .content(p.getContent())
                        .placeTitle(p.getPlaceName())
                        .dateTime(p.getDateTime())
                        .latitude(p.getLatitude())
                        .longitude(p.getLongitude())
                        .build())
                .collect(Collectors.toList());
    }
}
