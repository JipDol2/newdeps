package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.UserService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import lotte.newdevps.dto.user.response.UserDTO;
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
    @PostMapping("/signUp/{socialType}")
    public CommonResponseEntity<UserDTO> signUp(@PathVariable String socialType, @RequestBody UserSignUpDTO userDto){
        UserDTO user = userService.join(socialType.toUpperCase(), userDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.U001,user,0);
    }

}
