package com.sns.like.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {

	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
} 
