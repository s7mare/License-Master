package top.gzii.license.common.config;




import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author tuxusheng
**/
@MapperScan(basePackages = "top.gzii.license.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 添加MyBatis-Plus插件
     * 注意：需要同时配置分页插件和防全表更新插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL); // 设置数据库类型为MySQL
        paginationInnerInterceptor.setMaxLimit(1000L); // 设置最大单页限制数量，默认-1不限制
        paginationInnerInterceptor.setOverflow(true); // 溢出总页数后是否进行处理

        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 防全表更新与删除插件（可选但推荐）
//         BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
//         interceptor.addInnerInterceptor(blockAttackInnerInterceptor);

        return interceptor;
    }
}