package com.markany.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.markany.config.app.DBConfig;
import com.markany.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.markany.mysite.service","com.markany.mysite.repository","com.markany.mysite.aspect"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
	
}
	