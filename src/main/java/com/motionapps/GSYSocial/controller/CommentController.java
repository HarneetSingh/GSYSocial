package com.motionapps.GSYSocial.controller;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.vo.CommentVO;
import com.motionapps.GSYSocial.services.CommentService;

@Controller
@Path("/comment")
public class CommentController {
	
	
	private Long status;
	
	@Autowired
	private CommentService commentService;



	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(CommentVO commentVO)
	{
		status=commentService.addComment(commentVO);
		
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateComment(CommentVO commentVO)
	{
		status = commentService.updateComment(commentVO);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/delete")
	@Transactional
	public Response deleteComment(@QueryParam("commentId")String commentId) {
		
		status=commentService.deleteComment(commentId);
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
		return Response.status(200).entity(commentService.getAllComments()).build();
	}
	
	@GET
	@Path("/getCommentsByPost")
	@Transactional
	public Response getCommentsByPost(@QueryParam("postId")String postId)
	{
		return Response.status(200).entity(commentService.getCommentsByPost(postId)).build();
	}
	
	

}
