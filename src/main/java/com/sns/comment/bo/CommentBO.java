package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	// input: 파라미터들 
	// output: void
	public void addComment(int userId, int postId, String content) {
		commentMapper.insertComment(userId, postId, content);
	}
	
	// input: 글번호
	// output: List<CommentViewList> 
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 댓글들 가져옴 
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회 => Comment -> CommentView => list에 담음
		for (Comment commnet : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 1개 
			commentView.setComment(commnet);
			
			// 댓글쓰니
			UserEntity user = userBO.getUserEntityById(commnet.getUserId());
			commentView.setUser(user);
			
			// **********list에 넣기 
			commentViewList.add(commentView);
		}
		
		
		return commentViewList;
	}
}
