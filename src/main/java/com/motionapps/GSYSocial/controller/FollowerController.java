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
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.FollowerDao;
import com.motionapps.GSYSocial.dao.JointAccountDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;

@Controller
@Path("/")
public class FollowerController {
	
	@Autowired
	private FollowerDao followerDao;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private JointAccountDao jointAccountDao;
	

	public void setJointAccountDao(JointAccountDao jointAccountDao) {
		this.jointAccountDao = jointAccountDao;
	}

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
		jointAccountDao.incrementFollowCount(followerVO.getJointAccountId());
		userController.incrementFollowCount(followerVO.getUserId());
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
		jointAccountDao.decrementFollowCount(followerVO.getJointAccountId());
		userController.decrementFollowCount(followerVO.getUserId());
		return Response.ok().build();
		
	}
	
	@GET
	@Path("/jointAccountFollowers")
	public Response getJointAccountFollowers(@QueryParam("jointAccountId") String jointAccountId)
	{
		return Response.ok().entity(new UserSearchVO(followerDao.getJointAccountFollowers(jointAccountId))).type(MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/jointAccountsFollowedByUser")
	public Response getJointAccountsFollowedByUser(@QueryParam("userId") String userId)
	{
		return Response.ok().entity(new JointAccountSearchVO(followerDao.getJointAccountsFollowedByUser(userId))).type(MediaType.APPLICATION_JSON).build();
	}
	

}
