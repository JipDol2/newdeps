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
    private String placeName;
    private Double latitude;
    private Double longitude;
    private LocalDate dateTime;
    private String address;
    private String categoryName;
    private Double starRating;

    private List<String> imagesPath;

    @Builder
    public PostDTO(Long postId,
                   String content,
                   Long viewCount,
                   String placeName,
                   Double latitude,
                   Double longitude,
                   LocalDate dateTime,
                   String address,
                   String categoryName,
                   Double starRating,
                   List<String> imagesPath) {
        this.postId = postId;
        this.content = content;
        this.viewCount = viewCount;
        this.placeName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.address = address;
        this.categoryName = categoryName;
        this.starRating = starRating;
        this.imagesPath = imagesPath;
    }

    public static PostDTO toDto(Post post) {
        return PostDTO.builder()
                .postId(post.getId())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .placeName(post.getPlaceName())
                .dateTime(post.getDateTime())
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .address(post.getAddress())
                .categoryName(post.getCategoryName())
                .starRating(post.getStarRating())
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
                        .viewCount(p.getViewCount())
                        .placeName(p.getPlaceName())
                        .dateTime(p.getDateTime())
                        .latitude(p.getLatitude())
                        .longitude(p.getLongitude())
                        .address(p.getAddress())
                        .categoryName(p.getCategoryName())
                        .starRating(p.getStarRating())
                        .imagesPath(p.getImages().stream()
                                .map(image -> image == null ? null : image.getStoredFileName())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
