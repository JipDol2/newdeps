package lotte.newdevps.ui.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.AuthService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.auth.request.LoginRequestDTO;
import lotte.newdevps.dto.auth.response.TokenResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인(A001)
     */
    @PostMapping("/login/{socialType}")
    public CommonResponseEntity<TokenResponse> login(@PathVariable String socialType, @RequestBody LoginRequestDTO loginDto){
        TokenResponse token = authService.login(socialType, loginDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.A001,token,1);
    }
}
