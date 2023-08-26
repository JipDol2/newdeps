package lotte.newdevps.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.user.User;

@Getter
@NoArgsConstructor
public class UserFindDTO {

    private Long userId;
    private String loginId;
    private String nickname;

    private String imagePath;

    @Builder
    public UserFindDTO(Long userId, String loginId, String nickname, String imagePath) {
        this.userId = userId;
        this.loginId = loginId;
        this.nickname = nickname;
        this.imagePath = imagePath;
    }

    public static UserFindDTO toDto(User user){
        return UserFindDTO.builder()
                .userId(user.getId())
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .imagePath(user.getImage()==null ? null : user.getImage().getStoredFileName())
                .build();
    }
}
