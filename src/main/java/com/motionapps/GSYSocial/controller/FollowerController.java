package com.motionapps.GSYSocial.controller;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.FollowerDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;

@Controller
@Path("/")
public class FollowerController {
	
	@Autowired
	private FollowerDao followerDao;
	
	@Autowired
	private UserController userController;
	
	public void setFollowerDao(FollowerDao followerDao) {
		this.followerDao = followerDao;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	@POST
	@Path("/follow")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response followAccount(FollowerVO followerVO)
	{
		followerVO.setFollowId(UUID.randomUUID().toString());
		followerDao.followAccount(followerVO);
		userController.incrementFollowCount(followerVO.getEmailId());
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/unfollow")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response unfollowAccount(FollowerVO followerVO)
	{
		followerVO.setFollowId(UUID.randomUUID().toString());
		followerDao.unfollowAccount(followerVO);
		userController.decrementFollowCount(followerVO.getEmailId());
		return Response.ok().build();
		
	}

}
