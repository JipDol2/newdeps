package lotte.newdevps.ui.auth;

import lombok.Getter;

@Getter
public class LoginSession {

    private Long id;

    public LoginSession(Long id) {
        this.id = id;
    }

    public static LoginSession toUserSession(Long id){
        return new LoginSession(id);
    }
}
