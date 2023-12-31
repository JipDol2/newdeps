package lotte.newdevps.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.exception.file.ValidFile;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class UserProfileImageRequestDTO {

    @ValidFile(message = "EXU005")
    private MultipartFile file;

    public UserProfileImageRequestDTO(MultipartFile file) {
        this.file = file;
    }

}
