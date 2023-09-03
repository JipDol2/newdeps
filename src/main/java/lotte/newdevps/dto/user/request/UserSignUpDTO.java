package lotte.newdevps.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSignUpDTO {

    @NotEmpty(message = "EXU003")
    private String loginId;
    @NotEmpty(message = "EXU004")
    private String nickname;

    @Builder
    public UserSignUpDTO(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public static User toEntity(String socialType,UserSignUpDTO dto){
        return User.builder()
                .loginId(socialType+"_"+dto.getLoginId())
                .nickname(dto.getNickname())
                .build();
    }
}
