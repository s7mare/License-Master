package top.gzii.license.aop;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.gzii.license.annotation.ValidLicense;
import top.gzii.license.autofigure.LicenseProperty;
import top.gzii.license.common.license.LicenseVerify;
import top.gzii.license.context.LicenseStatusContext;
import top.gzii.license.enums.CheckModeEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@ConditionalOnProperty(prefix = "license" ,name="mode",havingValue = "annotation")
public class ValidAspect {
    private static final Logger logger= LoggerFactory.getLogger(ValidAspect.class);

    @Autowired
    LicenseStatusContext licenseStatusContext;
    @Autowired
    LicenseProperty licenseProperty;

    @Around("@annotation(validLicense)")
    public void aroundValid(ProceedingJoinPoint joinPoint, ValidLicense validLicense) {

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
                else
                //校验证书是否有效
                 verifyResult = licenseVerify.verify();

                if(verifyResult){
                    try {
                        joinPoint.proceed();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }else{
                    logger.error("证书校验失败!");
                   throw new RuntimeException("证书已失效，请联系系统管理员");
                }

    }





}
