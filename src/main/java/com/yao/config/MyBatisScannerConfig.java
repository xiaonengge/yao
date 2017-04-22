package com.yao.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaoqing on 17/4/21.
 * 扫描MyBatis的Mapper接口，我们就需要配置这个类，这个配置我们需要单独放到一个类中。
 */
@Configuration
//TODO 由于MyBatisScannerConfig执行的比较早，因此必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.yao.mapper");
        return mapperScannerConfigurer;
    }
}
