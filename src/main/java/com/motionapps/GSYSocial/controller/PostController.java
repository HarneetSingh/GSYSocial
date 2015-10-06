package com.motionapps.GSYSocial.controller;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.PostDao;
import com.motionapps.GSYSocial.dao.vo.PostArrayVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;

@Controller
@Path("/post")
public class PostController {
	
	@Autowired
	private PostDao postDao;

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPost(PostVO postVO)
	{
		postVO.setPostId(UUID.randomUUID().toString());
		Long status=postDao.createPost(postVO);
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
		Long status = postDao.updatePost(postVO);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/delete")
	public Response deletePost(@QueryParam("postId")String postId) {
		
		Long status = postDao.deletePost(postId);
		if(status==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
		
	}
	
	
	@GET
	@Path("/getPostByJointAccount")
	public Response getPostByJointAccount(@QueryParam("jointAccountId")String jointAccountId) {
		
		return Response.status(200).entity(new PostArrayVO(postDao.getPostByJointAccount(jointAccountId))).build();
		
	}
	
	@GET
	@Path("/getPostForUser")
	public Response getPostForUser(@QueryParam("emailId")String emailId) {
		
		return Response.status(200).entity(new PostArrayVO(postDao.getPostForUser(emailId))).build();
		
	}
	
	@GET
	@Path("/getAllPosts")
	public Response getAllPosts() {
		
		return Response.status(200).entity(new PostArrayVO(postDao.getAllPosts())).build();
		
	}
}
