package com.yao.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by yaoqing on 17/4/21.
 */
@SpringBootConfiguration
public class ApplicationConfig {
    @Bean
    public PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        ppc.setLocations(new Resource[]{new ClassPathResource("application.properties")});
        return ppc;
    }
}
