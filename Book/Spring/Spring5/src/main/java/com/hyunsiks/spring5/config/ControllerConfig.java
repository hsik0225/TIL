package com.hyunsiks.spring5.config;

import com.hyunsiks.spring5.controller.RegisterController;
import com.hyunsiks.spring5.spring.MemberRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

	@Autowired
	private MemberRegisterService memberRegSvc;

	@Bean
	public RegisterController registerController() {
		RegisterController controller = new RegisterController();
		controller.setMemberRegisterService(memberRegSvc);
		return controller;
	}
}
