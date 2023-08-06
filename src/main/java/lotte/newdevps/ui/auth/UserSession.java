package lotte.newdevps.ui.auth;

import lombok.Builder;
import lombok.Getter;
import lotte.newdevps.domain.user.User;

@Getter
public class UserSession {

    private Long id;

    public UserSession(Long id) {
        this.id = id;
    }

    public static UserSession toUserSession(Long id){
        return new UserSession(id);
    }
}
