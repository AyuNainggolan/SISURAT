package com.example;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
public class SisuratApplication {

	public static void main(String[] args) {
		SpringApplication.run(SisuratApplication.class, args);
	}
	
	@Bean
	 public MultipartConfigElement multipartConfigElement() {
	     return new MultipartConfigElement("");
	 }


}
