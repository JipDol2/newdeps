package lotte.newdevps.ui.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.AuthService;
import lotte.newdevps.dto.auth.request.LoginRequestDTO;
import lotte.newdevps.dto.auth.response.TokenResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{socialType}")
    public TokenResponse login(@PathVariable String socialType, @RequestBody LoginRequestDTO loginDto){
        return authService.login(socialType,loginDto);
    }
}
