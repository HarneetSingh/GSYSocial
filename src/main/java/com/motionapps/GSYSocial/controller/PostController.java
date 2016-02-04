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


import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.services.PostService;

@Controller
@Path("/post")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	private Long status;


	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPost(PostVO postVO)
	{
		Long status=postService.createPost(postVO);

		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePost(PostVO postVO)
	{
		Long status = postService.updatePost(postVO);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/delete")
	@Transactional
	public Response deletePost(@QueryParam("postId")String postId) {
		
		status=postService.deletePost(postId);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();	
	}
	
	
	@GET
	@Path("/getPostByAccount")
	public Response getPostByJointAccount(@QueryParam("accountId")String accountId) {
		
		return Response.status(200).entity(postService.getPostByAccount(accountId)).build();
		
	}

	
	@GET
	@Path("/getPostForUser")
	public Response getPostForUser(@QueryParam("userId")String userId) {
		
		return Response.status(200).entity(postService.getPostForUser(userId)).build();
		
	}
	
	@GET
	@Path("/getAllPosts")
	public Response getAllPosts() {
		
		return Response.status(200).entity(postService.getAllPosts()).build();
		
	}
	
	
	
}
