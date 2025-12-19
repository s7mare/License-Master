package top.gzii.license;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.InetAddress;


@SpringBootApplication(scanBasePackages = "top.gzii.license")

@EnableKnife4j
@Slf4j
public class WebApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ConfigurableApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
        long endTime=System.currentTimeMillis();

        long spendTime =(System.currentTimeMillis() - startTime) / 1000;
        String info ="应用启动完成，耗时：{}秒，链接：http://{}:{}{}/swagger-ui/index.html";
        String host="";
        try {


         host =    InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){
            host = "127.0.0.1";
            e.printStackTrace();
        }
        String port = ctx.getEnvironment().getProperty("server.port");
        String contextPath = ctx.getEnvironment().getProperty("server.servlet.context-path");

        log.info(info, spendTime, host, port, contextPath);
    }
}
