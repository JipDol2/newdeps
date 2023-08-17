package lotte.newdevps.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.exception.file.ValidFile;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UserProfileImageRequestDTO {

    @ValidFile(message = "EXU005")
    private MultipartFile file;

    public UserProfileImageRequestDTO(MultipartFile file) {
        this.file = file;
    }

}
