package com.markany.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.markany.config.web.MessageSourceConfig;
import com.markany.config.web.MvcConfig;
import com.markany.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.markany.mysite.controller", "com.markany.mysite.exception"})
@Import({MvcConfig.class, SecurityConfig.class, MessageSourceConfig.class})
public class WebConfig {
	
}
