package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	// input:  x
	// output: List<PostEntity>
	public List<PostEntity> getPostEntityList() {
		// 최신순으로 가져오기 위해서 
		return postRepository.findByOrderByIdDesc();
	}

}
