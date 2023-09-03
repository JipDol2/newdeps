package lotte.newdevps.dto.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.image.Image;
import lotte.newdevps.domain.image.ImageType;

@Getter
@NoArgsConstructor
public class ImageDTO {

    private String originalFileName;
    private String storedFileName;
    private String imagePath;
    private ImageType type;

    @Builder
    public ImageDTO(String originalFileName, String storedFileName, String imagePath, ImageType type) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.imagePath = imagePath;
        this.type = type;
    }

    public void setType(ImageType type){
        this.type = type;
    }

    public static Image toImageEntity(ImageDTO dto){
        return Image.builder()
                .originalFileName(dto.getOriginalFileName())
                .storedFileName(dto.getStoredFileName())
                .type(dto.getType())
                .imagePath(dto.getImagePath())
                .build();
    }
}
