package com.tsd.armsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class AutomatedResourcesManagementSystemApplication {

	@Value("${app.frontend}")
	String url ;
	public static void main(String[] args) {
		SpringApplication.run(AutomatedResourcesManagementSystemApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer crosConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
