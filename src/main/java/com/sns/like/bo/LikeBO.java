package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.Mapper.LikeMapper;


@Service
public class LikeBO {
	
	@Autowired 
	private LikeMapper likeMapper;

	// input: postId, userId
	// output: x
	public void likeToggle(int postId, int userId) {
		// 조회
		int count = likeMapper.selectLikeCountByPostIdOrUserId(postId,userId);
		
		// 여부 => 삭제 or 추가 
		if(count > 0) {
			// 있으면 삭제
			likeMapper.deleteLikeByPostIdUserId(postId,userId);
		} else {
			// 없으면 추가
			likeMapper.insertLike(postId,userId);
		}
	}
}
