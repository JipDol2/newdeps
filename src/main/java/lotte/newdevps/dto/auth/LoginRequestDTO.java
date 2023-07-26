package lotte.newdevps.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDTO {

    private Long id;

    public LoginRequestDTO(Long id) {
        this.id = id;
    }
}
