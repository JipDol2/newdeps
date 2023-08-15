package lotte.newdevps.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class UserProfileImageRequestDTO {

    private MultipartFile file;

    public UserProfileImageRequestDTO(MultipartFile file) {
        this.file = file;
    }
}
