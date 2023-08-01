package lotte.newdevps.domain.image;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    private Long id;

    private String originalFileName;

    private String storedFileName;

    @Enumerated(EnumType.STRING)
    private ImageType type;
}
