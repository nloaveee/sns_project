package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;


@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO;

	// http://localhost:8080/timeline/timeline-view
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model) {
		
		// db 조회 
		List<PostEntity> postList = postBO.getPostEntityList();
		
		// model에 담기 
		model.addAttribute("postList",postList);
		
		return "timeline/timeline";
	}
}
