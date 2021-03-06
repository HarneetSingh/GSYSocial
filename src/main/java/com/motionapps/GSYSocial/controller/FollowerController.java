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
	@Path("/acceptfollowrequest")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response acceptFollowRequest(@QueryParam("followerRequestId") String followerRequestId,@QueryParam("notificationId") String notificationId)
	{
		status=followerService.acceptFollowRequest(followerRequestId,notificationId);
		
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}
	
	@GET
	@Path("/rejectfollowrequest")
	public Response rejectFollowRequest(@QueryParam("followerRequestId") String followerRequestId,@QueryParam("notificationId") String notificationId)
	{
		status=followerService.rejectFollowRequest(followerRequestId,notificationId);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}

	@GET
	@Path("/accountFollowers")
	public Response getAccountFollowers(@QueryParam("accountId") String accountId)
	{
		return Response.ok().entity(followerService.getAccountFollowers(accountId)).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/accountsFollowedByUser")
	public Response getAccountsFollowedByUser(@QueryParam("userId") String userId)
	{
		return Response.ok().entity(followerService.getAccountsFollowedByUser(userId)).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getAllFollowers")
	public Response getAllFollowers()
	{
		return Response.ok().entity(followerService.getAllFollowers()).type(MediaType.APPLICATION_JSON).build();

	}
	

}
