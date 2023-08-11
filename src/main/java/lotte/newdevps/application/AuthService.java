package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.common.auth.JwtManager;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.auth.request.LoginRequestDTO;
import lotte.newdevps.dto.auth.response.TokenResponse;
import lotte.newdevps.exception.user.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final JwtManager jwtManager;
    private final UserRepository userRepository;

    public TokenResponse login(String socialType, LoginRequestDTO loginDto){
        String id = socialType+"_"+loginDto.getLoginId();
        User user = userRepository.findByLoginId(id)
                .orElseThrow(() -> new UserNotFoundException());

        //accessToken 발급
        String accessToken = jwtManager.createAccessToken(user.getId());
        return TokenResponse.from(accessToken);
    }

    public String getClaimsId(String token){
        return jwtManager.getId(token);
    }

    public void validationToken(String token){
        jwtManager.validationToken(token);
    }
}
