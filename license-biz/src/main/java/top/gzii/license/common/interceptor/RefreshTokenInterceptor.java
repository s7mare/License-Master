package top.gzii.license.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import top.gzii.license.common.constants.RedisConstants;
import top.gzii.license.common.context.LoginUserHolder;
import top.gzii.license.common.utils.RedisUtils;
import top.gzii.license.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            return true; // 直接放行预检请求
//        }
        // 打印所有请求头用于调试
        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }

        String token = request.getHeader("token");
        System.out.println("========"+token);
        String test=request.getHeader("test");
        if (StringUtils.isEmpty(token))
            return true;

        String key= RedisConstants.LOGIN+token;
        UserInfo userInfo = (UserInfo) redisUtils.get(key);

        if (ObjectUtils.isEmpty(userInfo))
            return true;
        //刷新令牌
        LoginUserHolder.set(userInfo);
        redisUtils.expire(key,RedisConstants.LOGIN_EXPIRE_TIME);
        return true;
    }
}
