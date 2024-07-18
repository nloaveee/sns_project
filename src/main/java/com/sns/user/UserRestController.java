package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	/**
	 * 중복확인
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(@RequestParam("loginId") String loginId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		// 응답값 
		Map<String, Object> result  = new HashMap<>();
		result.put("code", 200);
		if(user!=null) {
			result.put("is_duplicated_id", true);
		} else {
			result.put("is_duplicated_id", false);
		}
		
		return result;		
	}
	
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		
		// password 암호화 
		String hashedPassword = EncryptUtils.md5(password);
		
		
		// db insert 
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
		if ( user != null ) {
			result.put("code", 200);
			result.put("result", "성공");
			
		} else {
			result.put("code", 500);
			result.put("error_message","회원가입에 실패했습니다.");
		}
		
		return result;
	}
	
	
	// 로그인 API
	@RequestMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		
		// 비밀번호 hasing
		String hashedPassword = EncryptUtils.md5(password);
		
		// db select
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId,hashedPassword);
		
		// session
		session.setAttribute("userId", user.getId());     
		session.setAttribute("userLoginId", user.getLoginId());
		session.setAttribute("userName", user.getName());
		
		// 응답값 
		Map<String, Object> result = new HashMap<>();
	
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
