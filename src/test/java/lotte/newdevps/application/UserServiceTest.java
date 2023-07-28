package lotte.newdevps.application;

import lotte.newdevps.domain.user.User;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트 - 성공")
    void signUpTest() {
        //given
        UserSignUpDTO signUpDTO = UserSignUpDTO.builder()
                .loginId("1243532")
                .nickname("jipdol2")
                .build();

        User user = User.builder()
                .loginId("naver_1243532")
                .nickname("jipdol2")
                .build();

        //mocking
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByLoginId(signUpDTO.getLoginId())).thenReturn(Optional.ofNullable(user));

        //when
        userService.join(signUpDTO);

        //then
        User findByUser = userRepository.findByLoginId(signUpDTO.getLoginId())
                .orElse(null);

        assertThat(user.getLoginId()).isEqualTo(findByUser.getLoginId());
        assertThat(user.getNickname()).isEqualTo(findByUser.getNickname());
    }
}