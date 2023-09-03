package lotte.newdevps.ui.auth;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lotte.newdevps.application.AuthService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if((handler) instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoAuth noAuth = handlerMethod.getMethodAnnotation(NoAuth.class);
            if(noAuth != null){
                return true;
            }
        }

        String accessToken = request.getHeader("Authorization");
        authService.validationToken(accessToken);
        return true;
    }
}
