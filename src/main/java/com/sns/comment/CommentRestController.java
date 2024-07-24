package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;

	/**
	 * 댓글 쓰기 
	 * @param postId
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create") 
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session) {
		
		// 로그인 여부 검사
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 403);
			result.put("error_message", "로그인을 해주세요");
			return result;
		}
		
		// DB insert 
		commentBO.addComment(userId, postId, content);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	// 댓글 삭제 
//	@PostMapping("/delete")
//	public Map<String, Object> delete(@RequestParam("postId") int postId) {
//		
//	}
	
}
