package com.yao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * Created by yaoqing on 17/4/21.
 */
@Configuration
public class DataConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.minIdle}")
    private int maxIdle;

    @Value("${spring.datasource.maxWait}")
    private long maxWait;

    /**
     * 没有这个方法，容器里没有Datasource这个bean，因此在别的地方用@Autowired无法映射
     * 可能因为加了bean标签，因此spring容器里会存在这个bean
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setTestOnBorrow(true);
        return druidDataSource;
    }
}
