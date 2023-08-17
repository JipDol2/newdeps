package lotte.newdevps.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.comment.Comment;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.dto.post.request.PostUpdateDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long viewCount;

    private String placeName;

    private Double latitude;

    private Double longitude;

    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToOne(mappedBy = "post")
    private Bookmark bookmark;

    @Builder
    public Post(String content, String placeName, Double latitude, Double longitude, LocalDate dateTime, User user) {
        this.content = content;
        this.viewCount = 0L;
        this.placeName = placeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.user = user;
    }

    public void updatePost(PostUpdateDTO dto){
        this.content = dto.getContent();
        this.placeName = dto.getPlaceName();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
        this.dateTime = dto.getDateTime();
    }

    public void addImages(List<Image> images){
        images.forEach(image->image.setPost(this));
//        image.setPost(this);
    }

    public void setBookmark(Bookmark bookmark){
        this.bookmark = bookmark;
    }
}
