package lotte.newdevps.ui;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.UserService;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/signUp")
    public CommonResponseEntity signUp(@RequestBody UserSignUpDTO userDto){
        userService.join(userDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.U001,null,0);
    }

}
