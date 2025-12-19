package top.gzii.license.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.gzii.license.common.interceptor.AuthenticationInterceptor;
import top.gzii.license.common.interceptor.RefreshTokenInterceptor;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    RefreshTokenInterceptor refreshTokenInterceptor;
    @Autowired
    AuthenticationInterceptor authenticationInterceptor;
    @Value("${app.auth:true}")
    public boolean enableAuth;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(refreshTokenInterceptor).order(1).addPathPatterns("/**").excludePathPatterns("/user/login");
        if (enableAuth)
            registry.addInterceptor(authenticationInterceptor).order(2).excludePathPatterns("/user/login","/swagger-ui/*");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                // 允许跨域请求的域名或IP，星号代表允许所有
                .allowedOrigins("*")
                // 请求允许的方法，如GET, POST, PUT, DELETE等
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(false)
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*")
        ;
                // 是否发送cookie，通常与Shiro的认证相
    }
}
