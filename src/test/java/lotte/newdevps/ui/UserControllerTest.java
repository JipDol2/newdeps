package lotte.newdevps.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lotte.newdevps.application.UserService;
import lotte.newdevps.config.WebMvcConfig;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import lotte.newdevps.dto.user.response.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean({WebMvcConfig.class,JpaMetamodelMappingContext.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private static final String URL = "/api/user";

    @Test
    @DisplayName("회원가입 테스트 - 성공")
    void signUpTest() throws Exception {
        //given
        UserSignUpDTO signUpDTO = UserSignUpDTO.builder()
                .loginId("1243532")
                .nickname("jipdol2")
                .build();

        String json = objectMapper.writeValueAsString(signUpDTO);

        //userId,loginId,nickname
        //when
        when(userService.join(any(),any())).thenReturn(new UserDTO(1L,"1243532","jipdol2"));

        //expected
        mockMvc.perform(post(URL+"/signUp/github")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("U001"))
                .andExpect(jsonPath("$.message").value("회원가입 성공"))
                .andExpect(jsonPath("$.data.userId").value(1L))
                .andExpect(jsonPath("$.data.loginId").value("1243532"))
                .andExpect(jsonPath("$.data.nickname").value("jipdol2"))
                .andDo(print());
    }

}