package lotte.newdevps.application;

import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트 - 성공")
    void signUpTest(){
        //given
        UserSignUpDTO signUpDTO = UserSignUpDTO.builder()
                .loginId("1243532")
                .nickname("jipdol2")
                .build();

        //when
        userService.join(signUpDTO);

        //then
        User findByUser = userRepository.findByLoginId(signUpDTO.getLoginId())
                .orElse(null);

        assertThat(findByUser.getLoginId()).isEqualTo(signUpDTO.getLoginId());
        assertThat(findByUser.getNickname()).isEqualTo(signUpDTO.getNickname());
    }

}