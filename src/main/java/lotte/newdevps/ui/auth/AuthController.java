package lotte.newdevps.ui.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.AuthService;
import lotte.newdevps.dto.auth.LoginRequestDTO;
import lotte.newdevps.dto.auth.TokenResponse;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login/{socialType}")
    public TokenResponse login(@PathVariable String socialType, @RequestBody LoginRequestDTO loginDto){
        return authService.login(socialType,loginDto);
    }
}
