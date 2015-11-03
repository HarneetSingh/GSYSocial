package com.motionapps.GSYSocial.controller;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.CommentDao;
import com.motionapps.GSYSocial.dao.PostDao;
import com.motionapps.GSYSocial.dao.vo.CommentArrayVO;
import com.motionapps.GSYSocial.dao.vo.CommentVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;

@Controller
@Path("/comment")
public class CommentController {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private PostDao postDao;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(CommentVO commentVO)
	{
		commentVO.setCommentId(UUID.randomUUID().toString());
		Long status=commentDao.addComment(commentVO);
		postDao.incrementCommentCount(commentVO.getPostId());
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(CommentVO commentVO)
	{
		Long status = commentDao.updateComment(commentVO);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/delete")
	@Transactional
	public Response deletePost(@QueryParam("commentId")String commentId) {
		
		CommentVO commentVO = commentDao.getCommentById(commentId);
		Long status = commentDao.deleteComment(commentId);
		postDao.decrementCommentCount(commentVO.getCommentId());
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();	
	}
	
	
	@GET
	@Path("/getAllComments")
	@Transactional
	public Response getAllComments()
	{
		return Response.status(200).entity(new CommentArrayVO(commentDao.getAllComments())).build();
	}
	
	@GET
	@Path("/getCommentsByPost")
	@Transactional
	public Response getCommentsByPost(@QueryParam("postId")String postId)
	{
		return Response.status(200).entity(new CommentArrayVO(commentDao.getCommentsByPost(postId))).build();
	}
	
	

}
