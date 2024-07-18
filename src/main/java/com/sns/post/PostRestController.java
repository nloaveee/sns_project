package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {

	
	// 글쓰기 후 저장 
//	@PostMapping("/create")
//	public Map<String, Object> create(
//			@RequestParam("content") String content,
//			@RequestParam(value = "file", required = false) MultipartFile file,
//			HttpSession session) {
//		
//		// session에서 글쓴이 번호 꺼내기
//		
//		// DB INSERT
//		
//		// 응답값
//		Map<String,Object> result =new HashMap<>();
//		result.put("code", 200);
//		result.put("result", "성공");
//	}
}
