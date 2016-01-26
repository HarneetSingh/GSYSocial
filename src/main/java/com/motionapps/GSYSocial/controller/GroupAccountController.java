package com.motionapps.GSYSocial.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.services.GroupAccountService;

@Controller
@Path("/groupaccount")
public class GroupAccountController {
	
	
	private Long status;


	@Autowired
	private GroupAccountService groupAccountService;
	

	
	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGroupAccount(GroupAccountVO groupAccountVO)
	{
		status=groupAccountService.createGroupAccount(groupAccountVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGroupAccount(GroupAccountVO groupAccountVO)
	{
		status=groupAccountService.updateGroupAccount(groupAccountVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchGroupAccount(@QueryParam("keyword")String keyword)
	{
		return Response.ok().entity(groupAccountService.searchGroupAccount(keyword)).build();
	}
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupAccount(@QueryParam("groupAccountId")String groupAccountId)
	{
		return Response.ok().entity(groupAccountService.getGroupAccount(groupAccountId)).build();
	}
	
	@GET
	@Path("/detailsWithUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJointAccount(@QueryParam("groupAccountId")String groupAccountId,@QueryParam("userId")String userId) {
		return  Response.ok().entity(groupAccountService.getGroupAccountWithUserId(groupAccountId,userId)).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupAccounts()
	{
		return Response.ok().entity(new GroupAccountSearchVO(groupAccountService.getGroupAccounts())).build();
	}
	
	@GET
	@Path("/getgroupaccounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupAccounts(@QueryParam("userId")String userId)
	{
		return Response.ok().entity(new GroupAccountSearchVO(groupAccountService.getGroupAccountsByUserId(userId))).build();
	}
	
	
	
	
	@GET
	@Path("/getmembers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupMembers(@QueryParam("groupAccountId")String groupAccountId )
	{
		return Response.ok().entity(groupAccountService.getGroupMembers(groupAccountId)).build();
	}
	

	
	@GET
	@Path("/searchusers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(@QueryParam("groupAccountId")String groupAccountId,@QueryParam("keyword")String keyword )
	{
		return Response.ok().entity(new UserSearchVO(groupAccountService.searchUsers(groupAccountId,keyword))).build();
	}
	
	@POST
	@Path("/invite")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteGroupMember(GroupMemberVO groupMemberVO)
	{
		status=groupAccountService.inviteGroupMember(groupMemberVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}
	
	@GET
	@Path("/accept")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteAccepted(@QueryParam("groupAccountId")String groupAccountId,@QueryParam("userId")String userId,@QueryParam("notificationId") String notificationId)
	{
		status=groupAccountService.inviteAccepted(groupAccountId,userId,notificationId);
		
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
		
	}
	
	@GET
	@Path("/reject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteRejected(@QueryParam("groupAccountId")String groupAccountId,@QueryParam("userId")String userId,@QueryParam("notificationId") String notificationId)
	{
		status=groupAccountService.inviteRejected(groupAccountId,userId,notificationId);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
		
	}
	
	@POST
	@Path("/removeMember")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeMember(GroupMemberVO groupMemberVO)
	{
		status=groupAccountService.removeMember(groupMemberVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
		
	}
	

}
