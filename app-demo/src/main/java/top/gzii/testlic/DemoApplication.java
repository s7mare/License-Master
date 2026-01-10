package top.gzii.testlic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.gzii.license.annotation.EnableLicense;


/**
 *  集成示例demo
 *
 */


@SpringBootApplication
@EnableLicense
public class DemoApplication {
   private static Logger logger= LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String args[]){
        SpringApplication.run(DemoApplication.class,args);
        logger.info("应用启动成功!");
    }
}
