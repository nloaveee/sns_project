package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.mapper.PostMapper;
import com.sns.post.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManagerService;

	
	// input:  x
	// output: List<PostEntity>
	public List<PostEntity> getPostEntityList() {
		// 최신순으로 가져오기 위해서 
		return postRepository.findByOrderByIdDesc();
	}
	
	// input: 파라미터들 
	// output: PostEntity
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {

		// 업로드 후 imagePath를 받아옴
		String imagePath = fileManagerService.uploadFile(file, userLoginId);

		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// 글 삭제 
	// input: userId, postId
	// output: x
	@Transactional
	public void deletePost(int postId, Integer userId) {
		
		// 기존 글 가져오기 
		PostEntity postEntity = postRepository.findByIdAndUserId(postId,userId);
		if (postEntity == null) {
			log.info("[글 삭제] post is null. postId: {}, userId: {}", postId, userId );
			return;
		}
		
		// 글 삭제
		postRepository.deleteById(postEntity.getId());
		
		// 댓글 삭제 
		commentBO.deleteCommentByPostId(postEntity.getId());
		
		// 좋아요 삭제 
		likeBO.deleteLikeByPostId(postEntity.getId());
		
		// 이미지 삭제 
		fileManagerService.deleteFile(postEntity.getImagePath());
		
	}
}
