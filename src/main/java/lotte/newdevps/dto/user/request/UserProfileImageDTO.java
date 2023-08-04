package lotte.newdevps.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class UserProfileImageDTO {

    private MultipartFile file;

    public UserProfileImageDTO(MultipartFile file) {
        this.file = file;
    }
}
