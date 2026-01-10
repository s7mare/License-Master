package top.gzii.license.annotation;


import org.springframework.context.annotation.Import;
import top.gzii.license.autofigure.LicenseConfiguration;

import java.lang.annotation.*;

/**
 * @author luoyu
 *  标注在主启动类上以激活版权保护模块
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LicenseConfiguration.class)
public @interface EnableLicense {
}
