package com.yao;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 因为这个启动类已经在最外层，包含所有子包，
 * springboot会自动加载启动类所在包下及其子包下的所有组件
 * 如果这时候加上下面的注解，会报错404
 */
//@ComponentScan(basePackages = {"com.yao.config.ApplicationConfig", "com.yao.config.DataConfig",
//		"com.yao.config.MyBatisConfig", "com.yao.config.MyBatisScannerConfig"})
public class DemoyaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoyaoApplication.class, args);
	}
}
