package lotte.newdevps.dto.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileImageResponseDTO {

    private String profileImagePath;

    public UserProfileImageResponseDTO(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
