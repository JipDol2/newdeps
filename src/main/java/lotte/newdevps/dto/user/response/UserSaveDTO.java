package lotte.newdevps.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSaveDTO {

    private Long userId;
    private String loginId;
    private String nickname;

    @Builder
    public UserSaveDTO(Long userId, String loginId, String nickname) {
        this.userId = userId;
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public static UserSaveDTO toDto(User user){
        return UserSaveDTO.builder()
                .userId(user.getId())
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .build();
    }
}
