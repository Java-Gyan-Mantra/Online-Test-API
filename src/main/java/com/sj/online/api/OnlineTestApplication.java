package com.sj.online.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class OnlineTestApplication extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/Java_Gyan_Mantra/MockTest").setViewName(
				"/home");
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineTestApplication.class, args);
	}
}
