package lotte.newdevps.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponse {

    private String accessToken;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenResponse from(String accessToken){
        return new TokenResponse(accessToken);
    }
}
