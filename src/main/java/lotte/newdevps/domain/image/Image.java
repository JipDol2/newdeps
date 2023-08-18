package lotte.newdevps.domain.image;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.user.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "IMAGE")
public class Image {

    @Id @GeneratedValue
    private Long id;

    private String originalFileName;

    private String storedFileName;

    private String imagePath;

    @Enumerated(EnumType.STRING)
    private ImageType type;

    @OneToOne(mappedBy = "image", fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    @Builder
    public Image(String originalFileName, String storedFileName, ImageType type,String imagePath) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.type = type;
        this.imagePath = imagePath;
    }

    public void setPost(Post post){
        if(this.post != null){
            this.post.getImages().remove(this);
        }
        this.post = post;
        post.getImages().add(this);
    }
}
