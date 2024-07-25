package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

import jakarta.servlet.http.HttpSession;


@Controller
public class TimelineController {
	
	@Autowired
	private TimelineBO timelineBO;

	// http://localhost:8080/timeline/timeline-view
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model, HttpSession session) {
		
		// int로 하면 비로그인이 들어오는 경우 null이라서 error가 발생하기 때문에 
		// Integer로 해줘야 한다.
		 Integer userId = (Integer)session.getAttribute("userId");
		
		// db 조회 
		List<CardView> cardViewList = timelineBO.generateCardViewList(userId);
		
		// model에 담기 
		model.addAttribute("cardViewList",cardViewList);
		
		return "timeline/timeline";
	}
}
