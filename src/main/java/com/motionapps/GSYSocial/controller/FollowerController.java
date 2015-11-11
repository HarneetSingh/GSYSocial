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


import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.services.FollowerService;


@Controller
@Path("/")
public class FollowerController {
	
	@Autowired
	private FollowerService followerService;
	
	private Long status;
	
	
	public void setFollowerService(FollowerService followerService) {
		this.followerService = followerService;
	}

	@POST
	@Path("/follow")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response followAccount(FollowerVO followerVO)
	{
		status=followerService.followAccount(followerVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
		
	}
	
	@POST
	@Path("/unfollow")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unfollowAccount(FollowerVO followerVO)
	{

		status=followerService.unfollowAccount(followerVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/jointAccountFollowers")
	public Response getJointAccountFollowers(@QueryParam("jointAccountId") String jointAccountId)
	{
		return Response.ok().entity(followerService.getJointAccountFollowers(jointAccountId)).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/jointAccountsFollowedByUser")
	public Response getJointAccountsFollowedByUser(@QueryParam("userId") String userId)
	{
		return Response.ok().entity(followerService.getJointAccountsFollowedByUser(userId)).type(MediaType.APPLICATION_JSON).build();
	}
	

}
