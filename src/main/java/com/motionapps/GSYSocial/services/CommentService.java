package com.motionapps.GSYSocial.services;

import java.util.UUID;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.CommentDao;
import com.motionapps.GSYSocial.dao.vo.CommentArrayVO;
import com.motionapps.GSYSocial.dao.vo.CommentVO;

public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}


	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}


	public Long addComment(CommentVO commentVO)
	{
		commentVO.setCommentId(UUID.randomUUID().toString());
		commentDao.addComment(commentVO);
		return postService.incrementCommentCount(commentVO.getPostId());

	}
	
	public Long updateComment(CommentVO commentVO)
	{
		return  commentDao.updateComment(commentVO);

		
	}
	
	
	public CommentArrayVO getAllComments()
	{
		return new CommentArrayVO(commentDao.getAllComments());
	}
	
	
	public CommentArrayVO getCommentsByPost(@QueryParam("postId")String postId)
	{
		return new CommentArrayVO(commentDao.getCommentsByPost(postId));
	}
	
	public Long deleteComment(String commentId) {
		
		CommentVO commentVO = commentDao.getCommentById(commentId);
		Long status = commentDao.deleteComment(commentId);
		postService.decrementCommentCount(commentVO.getCommentId());
		return status;
	}
	
	public Long deleteAllCommentsByPost(String postId)
	{
		return commentDao.deleteAllCommentsByPost(postId);
	}
	

	
	
}
