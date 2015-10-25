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

import com.motionapps.GSYSocial.dao.InviteRequestDao;
import com.motionapps.GSYSocial.dao.vo.ErrorVO;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

@Controller
@Path("/invite")
public class InviteRequestController {
	
	@Autowired
	public InviteRequestDao inviteRequestDao;
	@Autowired
	public JointAccountController jointAccountController;
	@Autowired
	public UserController userController;
	
	private JointAccountVO jointAccountVO;
	
	
	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public void setInviteRequestDao(InviteRequestDao inviteRequestDao) {
		this.inviteRequestDao = inviteRequestDao;
	}
	
	public void setJointAccountController(
			JointAccountController jointAccountController) {
		this.jointAccountController = jointAccountController;
	}
	
	@POST
	@Path("/request")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteUser(InviteRequestVO inviteRequestVO)
	{
		String inviteRequestId=UUID.randomUUID().toString();
		inviteRequestVO.setInviteRequestId(inviteRequestId);
		Long result=inviteRequestDao.inviteUser(inviteRequestVO);
//		InviteRequestVO tempInviteRequestVO=new InviteRequestVO();
//		tempInviteRequestVO.setInviteRequestId(inviteRequestId);
		
		
		//Setting the invite_request_pending flag in user table to true
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.TRUE);
		userController.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userController.updateUserDetails(userVO);

		if(result==1)
			return Response.status(200).entity(inviteRequestVO).type(MediaType.APPLICATION_JSON).build();
		else
			return Response.status(400).build();
	}
	
	@POST
	@Path("/accept")
	@Transactional 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteAccepted(InviteRequestVO inviteRequestVO)
	{
//		inviteRequestVO.setInviteAccepted(Boolean.TRUE);
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userController.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userController.updateUserDetails(userVO);
		
		
		Long result=inviteRequestDao.inviteDeleted(inviteRequestVO);		

		
		
		if(result==1){
			//Setting the invite_request_pending flag in user table to false

			//inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
			jointAccountVO=jointAccountController.createJointAccount(inviteRequestVO);
			return Response.ok().entity(jointAccountVO).type(MediaType.APPLICATION_JSON).build();
			
		}else
			{
			ErrorVO errorVO=new ErrorVO("No Pending Request Found");
			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}

	}
	

	
	@POST
	@Path("/{a:reject|cancel}")
	@Transactional 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteRejected(InviteRequestVO inviteRequestVO)
	{
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());

		//Setting the invite_request_pending flag in user table to false
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userController.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userController.updateUserDetails(userVO);
		
		
		Long result=inviteRequestDao.inviteDeleted(inviteRequestVO);
		if(result==1){
			return Response.ok().build();
		}else
		{
			ErrorVO errorVO=new ErrorVO("No Pending Request Found");
			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
		}

	}
	
	@GET
	@Path("/pending")
	public Response pendingRequests(@QueryParam("userId") String userId)
	{
		InviteRequestVO inviteRequestVO=inviteRequestDao.getInviteRequest(userId);
		return Response.status(200).entity(inviteRequestVO).type(MediaType.APPLICATION_JSON).build();
	}
	
}
