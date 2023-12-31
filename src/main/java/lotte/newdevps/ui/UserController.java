package lotte.newdevps.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.UserService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.user.request.UserProfileImageRequestDTO;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import lotte.newdevps.dto.user.response.UserFindDTO;
import lotte.newdevps.dto.user.response.UserSaveDTO;
import lotte.newdevps.dto.user.response.UserProfileImageResponseDTO;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.LoginSession;
import lotte.newdevps.ui.auth.NoAuth;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 유저 회원가입(U001)
     */
    @NoAuth
    @PostMapping("/signUp/{socialType}")
    public CommonResponseEntity<UserSaveDTO> signUp(@PathVariable String socialType, @RequestBody @Valid UserSignUpDTO userDto){
        UserSaveDTO user = userService.join(socialType.toUpperCase(), userDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.U001,user,0);
    }

    /**
     * 유저 프로필 사진 업로드(U002)
     */
    @PostMapping("/profile/image")
    public CommonResponseEntity<UserProfileImageResponseDTO> uploadProfileImage(@Authentication LoginSession session,
                                                                                @ModelAttribute @Valid UserProfileImageRequestDTO imageDTO){
        return CommonResponseEntity.toResponseEntity(ResponseType.U002,userService.saveProfileImage(session,imageDTO),1);
    }

    /**
     * 유저 정보 조회(U003)
     */
    @GetMapping("/profile")
    public CommonResponseEntity<UserFindDTO> findByUserInfo(@Authentication LoginSession session){
        return CommonResponseEntity.toResponseEntity(ResponseType.U003,userService.findByUser(session),1);
    }
}
