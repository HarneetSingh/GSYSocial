package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.CommentVO;

public interface CommentDao {
	
	public Long addComment(CommentVO commentVO);
	
	public CommentVO getCommentById(String commentId);
	
	public List<CommentVO> getAllComments();
	
	public List<CommentVO> getCommentsByPost(String postId);
	
	public List<CommentVO> getCommentsByUserId(String userId);

	public Long updateComment(CommentVO commentVO);

	public Long deleteComment(String commentId);
	
	public Long deleteAllCommentsByPost(String postId);
	

}
