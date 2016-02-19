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

import com.motionapps.GSYSocial.dao.vo.ErrorVO;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.services.InviteRequestService;

@Controller
@Path("/invite")
public class InviteRequestController {
	
	
	private Long result;
	
	@Autowired
	public InviteRequestService inviteRequestService;

		
	public void setInviteRequestService(InviteRequestService inviteRequestService) {
		this.inviteRequestService = inviteRequestService;
	}



	@POST
	@Path("/request")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteUser(InviteRequestVO inviteRequestVO)
	{
		Object object=inviteRequestService.inviteUser(inviteRequestVO);
		if(object instanceof ErrorVO)
		{	ErrorVO temp=(ErrorVO)object;
			return Response.status(temp.getStatus()).entity(temp).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/accept")
	@Transactional 
	public Response inviteAccepted(@QueryParam("inviteRequestId") String inviteRequestId,@QueryParam("notificationId") String notificationId)
	{

		JointAccountVO jointAccountVO=inviteRequestService.inviteAccepted(inviteRequestId,notificationId);
		if(jointAccountVO!=null)
			return Response.ok().entity(jointAccountVO).type(MediaType.APPLICATION_JSON).build();
		else
		{
			ErrorVO errorVO=new ErrorVO(400,"No Pending Request Found");
			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
		}
	}
	

	
	@GET
	@Path("/{a:reject|cancel}")
	@Transactional 
	public Response inviteRejected(@QueryParam("inviteRequestId") String inviteRequestId,@QueryParam("notificationId") String notificationId)
	{
		
		result=inviteRequestService.inviteRejected(inviteRequestId,notificationId);
		
		if(result==1){
			
			return Response.ok().build();
		}else
		{
			ErrorVO errorVO=new ErrorVO(400,"No Pending Request Found");
			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
		}

	}
	
	@GET
	@Path("/pending")
	public Response pendingRequests(@QueryParam("userId") String userId)
	{
		return Response.status(200).entity(inviteRequestService.pendingRequests(userId)).type(MediaType.APPLICATION_JSON).build();
	}
	

	
}
