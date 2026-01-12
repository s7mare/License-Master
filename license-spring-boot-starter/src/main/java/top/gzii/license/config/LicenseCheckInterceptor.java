package top.gzii.license.config;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.gzii.license.autofigure.LicenseProperty;
import top.gzii.license.common.license.LicenseVerify;
import top.gzii.license.context.LicenseStatusContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LicenseCheckInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LogManager.getLogger(LicenseCheckInterceptor.class);
    private LicenseProperty licenseProperty;
    private LicenseStatusContext licenseStatusContext;
    public LicenseCheckInterceptor(LicenseProperty licenseProperty,LicenseStatusContext licenseStatusContext) {
        this.licenseProperty=licenseProperty;
        this.licenseStatusContext= licenseStatusContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LicenseVerify licenseVerify = new LicenseVerify();
        boolean verifyResult;
        if (licenseProperty.isCacheEnabled()){
            //使用缓存
            if (licenseStatusContext.needUpdate()){
                verifyResult=  licenseVerify.verify();
                licenseStatusContext.setOk(verifyResult);
                licenseStatusContext.setLastVerifyTime(new Date());
            }
            else
                verifyResult= licenseStatusContext.isOk();
        }


        //校验证书是否有效
        else
       verifyResult=  licenseVerify.verify();

        if(verifyResult){
            return true;
        }else{
            response.setCharacterEncoding("gbk");
            Map<String,String> result = new HashMap<>(1);
            result.put("result","您的证书无效，请核查服务器是否取得授权或重新申请证书！");
            response.getWriter().write(JSON.toJSONString(result));
            logger.error("证书验证失败!");
            return false;
        }
    }
}
