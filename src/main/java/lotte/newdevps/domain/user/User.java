package lotte.newdevps.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.BaseTimeEntity;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.itinerary.Itinerary;
import lotte.newdevps.domain.post.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Itinerary> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "image_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Image image;

    @Builder
    public User(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public void setImage(Image image){
        this.image = image;
    }
}
