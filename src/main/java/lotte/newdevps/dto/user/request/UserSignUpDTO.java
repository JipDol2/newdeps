package lotte.newdevps.dto.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSignUpDTO {

    private String loginId;
    private String nickname;

    @Builder
    public UserSignUpDTO(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public static User toEntity(UserSignUpDTO dto){
        return User.builder()
                .loginId(dto.getLoginId())
                .nickname(dto.getNickname())
                .build();
    }
}
