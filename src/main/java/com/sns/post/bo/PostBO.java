package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
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
}
