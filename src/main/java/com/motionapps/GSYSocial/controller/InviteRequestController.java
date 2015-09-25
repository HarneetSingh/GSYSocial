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

import com.motionapps.GSYSocial.dao.InviteRequestDao;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;

@Controller
@Path("/invite")
public class InviteRequestController {
	
	@Autowired
	public InviteRequestDao inviteRequestDao;
	@Autowired
	public JointAccountController jointAccountController;
	
	
	public void setInviteRequestDao(InviteRequestDao inviteRequestDao) {
		this.inviteRequestDao = inviteRequestDao;
	}
	
	public void setJointAccountController(
			JointAccountController jointAccountController) {
		this.jointAccountController = jointAccountController;
	}
	
	@POST
	@Path("/request")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteUser(InviteRequestVO inviteRequestVO)
	{
		inviteRequestVO.setInviteRequestId(UUID.randomUUID().toString());
		Long result=inviteRequestDao.inviteUser(inviteRequestVO);
		if(result==1)
			return Response.ok().build();
		else
			return Response.status(400).build();
	}
	
	@POST
	@Path("/accept")
	@Transactional 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteAccepted(InviteRequestVO inviteRequestVO)
	{
		inviteRequestVO.setInviteAccepted(Boolean.TRUE);
		Long result=inviteRequestDao.inviteAccepted(inviteRequestVO);
		if(result==1){
			inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
			result=jointAccountController.createJointAccount(inviteRequestVO);
			if(result==1)
				return Response.ok().build();
			else
				return Response.status(400).build();
		}else
			return Response.status(400).build();

	}
	
	@POST
	@Path("/reject")
	@Transactional 
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteRejected(InviteRequestVO inviteRequestVO)
	{
		inviteRequestVO.setInviteAccepted(Boolean.TRUE);
		Long result=inviteRequestDao.inviteRejected(inviteRequestVO);
		if(result==1){
			return Response.ok().build();
		}else
			return Response.status(400).build();

	}
	
	
}
