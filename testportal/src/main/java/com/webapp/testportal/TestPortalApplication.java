package com.webapp.testportal;

import com.webapp.testportal.daoservice.serviceimpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TestPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPortalApplication.class, args);
		passwordEncoder();
	}
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
