package com.hyunsiks.spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Spring5Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Spring5Application.class, args);
	}

}
