package top.gzii.license.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import top.gzii.license.common.constants.RedisConstants;
import top.gzii.license.common.context.LoginUserHolder;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.common.utils.RedisUtils;
import top.gzii.license.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtils redisUtils;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true; // 直接放行预检请求
        }
        // 判断静态资源映射
        if (handler instanceof ResourceHttpRequestHandler) {
            // 直接过滤
            return true;


        }
        UserInfo userInfo = LoginUserHolder.get();


        if (ObjectUtils.isEmpty(userInfo)) {
            throw new LicenseException(ResultCodeEnum.NO_LOGIN);
        }
        return true;


    }
}
