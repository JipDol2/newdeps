package lotte.newdevps.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lotte.newdevps.application.UserService;
import lotte.newdevps.dto.user.request.UserSignUpDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean({JpaMetamodelMappingContext.class})
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

        //when
        doNothing().when(userService).join(any(),any());

        //expected
        mockMvc.perform(post(URL+"/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(status().isOk())
                .andDo(print());
    }

}