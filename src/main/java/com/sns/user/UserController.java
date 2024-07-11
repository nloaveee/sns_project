package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 화면용 Controller
@Controller
@RequestMapping("/user")
public class UserController {

	//http://localhost:8080/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	
	@GetMapping("/sign-in-view")
	public String singInView() {
		return "user/signIn";
	}
}
