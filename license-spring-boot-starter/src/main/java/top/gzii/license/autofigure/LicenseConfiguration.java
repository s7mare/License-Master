package top.gzii.license.autofigure;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.gzii.license.aop.ValidAspect;
import top.gzii.license.config.LicenseCheckInterceptor;
import top.gzii.license.config.LicenseCheckListener;
import top.gzii.license.context.LicenseStatusContext;

@Configuration
@EnableAspectJAutoProxy
@ConditionalOnWebApplication
@ComponentScan("top.gzii.license")
public class LicenseConfiguration  {
    private static final Logger logger= LoggerFactory.getLogger(LicenseConfiguration.class);

 @Bean
 @ConditionalOnMissingBean
   public LicenseCheckInterceptor licenseCheckInterceptor(LicenseProperty licenseProperty, LicenseStatusContext licenseStatusContext){
     return new LicenseCheckInterceptor(licenseProperty,licenseStatusContext);
 }


    @Bean
    @ConditionalOnProperty(prefix = "license",name = "mode",havingValue = "global")
    public WebMvcConfigurer licenseWebMvcConfigurer(LicenseCheckInterceptor interceptor) {
        logger.info("创建 License WebMvcConfigurer");
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                logger.info("注册 License 拦截器到拦截器链");
                registry.addInterceptor(interceptor)
                        .addPathPatterns("/**")
                        .excludePathPatterns("/error", "/public/**");
            }
        };
    }

}
