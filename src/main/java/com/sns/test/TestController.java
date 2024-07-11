package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class TestController {

	@Autowired
	PostMapper postMapper;

	//http://localhost:8080/test1
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "Hello world!!";
	}
	
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("a", 111);
		map.put("b", 222);
		map.put("c", 333);
		return map;
	}
	
	// 화면의 경우에는 ResponseBody x 
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostListTest();
	}
	
}
