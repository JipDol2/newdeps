package lotte.newdevps.config;

import lombok.RequiredArgsConstructor;
import lotte.newdevps.application.AuthService;
import lotte.newdevps.domain.user.UserRepository;
import lotte.newdevps.ui.auth.AuthController;
import lotte.newdevps.ui.auth.AuthInterceptor;
import lotte.newdevps.ui.auth.AuthenticationArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${com.upload.path}")
    private String imagePath;
    private final AuthService authService;
//    private final UserRepository userRepository;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationArgumentResolver(authService));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(authService))
                .excludePathPatterns("/static/**","/css/**")
                .addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///home/newdeps/upload/");
    }
}
