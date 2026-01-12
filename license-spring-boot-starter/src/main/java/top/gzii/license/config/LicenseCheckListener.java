package top.gzii.license.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import top.gzii.license.autofigure.LicenseConfiguration;
import top.gzii.license.autofigure.LicenseProperty;
import top.gzii.license.common.license.LicenseVerify;
import top.gzii.license.common.license.LicenseVerifyParam;
/**
 * 在项目启动时安装证书
 *
 * @author luoyu
 * @date 2026-1-7
 * @since 1.0.0
 */
@Component
@ConditionalOnWebApplication
public class LicenseCheckListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LogManager.getLogger(LicenseCheckListener.class);

 @Autowired
    LicenseProperty licenseProperty;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


            if(StringUtils.isNotEmpty(licenseProperty.getLicensePath())&&licenseProperty.getLicensePath().endsWith("lic")){
                logger.info("++++++++ 开始安装证书 ++++++++");

                LicenseVerifyParam param = new LicenseVerifyParam();
                param.setSubject(licenseProperty.getSubject());
                param.setPublicAlias(licenseProperty.getPublicAlias());
                param.setStorePass(licenseProperty.getStorePass());
                param.setLicensePath(licenseProperty.getLicensePath());
                param.setPublicKeysStorePath(licenseProperty.getPublicKeysStorePath());


                LicenseVerify licenseVerify = new LicenseVerify();
                //安装证书
                licenseVerify.install(param);

                logger.info("++++++++ 证书安装结束 ++++++++");
                logger.info("激活证书验证模块，模式：{}",licenseProperty.getMode());
            }
            else {
                logger.error("++++++证书路径或名称为空或文件类型错误，证书安装失败+++++++");
                throw new RuntimeException("证书安装失败");
            }

    }
}