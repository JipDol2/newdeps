package lotte.newdevps.dto.auth.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDTO {

    private String loginId;

    public LoginRequestDTO(String loginId) {
        this.loginId = loginId;
    }
}
