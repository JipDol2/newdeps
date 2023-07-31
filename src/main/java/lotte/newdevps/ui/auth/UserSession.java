package lotte.newdevps.ui.auth;

import lombok.Builder;
import lombok.Getter;
import lotte.newdevps.domain.user.User;

@Getter
public class UserSession {

    private Long id;
    private String loginId;
    private String nickname;

    public UserSession(Long id,String loginId, String nickname) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public static UserSession toUserSession(User user){
        return new UserSession(user.getId(),user.getLoginId(),user.getNickname());
    }
}
