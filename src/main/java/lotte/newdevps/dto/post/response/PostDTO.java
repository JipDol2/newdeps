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

    private Long postId;
    private String content;
    private Long viewCount;
    private String placeTitle;
    private Double latitude;
    private Double longitude;
    private LocalDate dateTime;

    private List<String> imagesPath;

    @Builder
    public PostDTO(Long postId,
                   String content,
                   Long viewCount,
                   String placeTitle,
                   Double latitude,
                   Double longitude,
                   LocalDate dateTime,
                   List<String> imagesPath) {
        this.postId = postId;
        this.content = content;
        this.viewCount = viewCount;
        this.placeTitle = placeTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.imagesPath = imagesPath;
    }

    public static PostDTO toDto(Post post) {
        return PostDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .placeTitle(post.getPlaceName())
                .dateTime(post.getDateTime())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .imagesPath(post.getImages().stream()
                        .map(image -> image == null ? null : image.getStoredFileName())
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<PostDTO> toDtoList(List<Post> posts) {
        return posts.stream()
                .map(p -> PostDTO.builder()
                        .postId(p.getId())
                        .content(p.getContent())
                        .placeTitle(p.getPlaceName())
                        .dateTime(p.getDateTime())
                        .latitude(p.getLatitude())
                        .longitude(p.getLongitude())
                        .imagesPath(p.getImages().stream()
                                .map(image -> image == null ? null : image.getStoredFileName())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
