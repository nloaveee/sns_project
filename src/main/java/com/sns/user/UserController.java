package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

// 화면용 Controller
@Controller
@RequestMapping("/user")
public class UserController {

	
	/**
	 * 회원가입 화면 
	 * @return
	 */
	//http://localhost:8080/user/sign-up-view
	@GetMapping("/sign-up-view")
	public String signUpView() {
		return "user/signUp";
	}
	
	/**
	 * 로그인 화면 
	 * @return
	 */
	@GetMapping("/sign-in-view")
	public String singInView() {
		return "user/signIn";
	}
	
	@RequestMapping("sign-out")
	public String singOut(HttpSession session) {
		
		// session 내용 비움
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		// 로그인 페이지로 redirect
		return "redirect:/user/sign-in-view";
	}
}
