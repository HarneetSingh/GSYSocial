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

import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.services.JointAccountService;


@Controller
@Path("/account")
public class JointAccountController {
	
	
	@Autowired
	public JointAccountService jointAccountService;

	private Long status;

	
	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}

	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJointAccountDetails(JointAccountVO jointAccountVO)
	{
		status=jointAccountService.updateJointAccountDetails(jointAccountVO);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();
	}
	
	/************************************ READ ************************************/
	/**
	 * Returns all resources (questions) from the database
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJointAccounts() {
		return Response.ok().entity(jointAccountService.getJointAccounts()).build();
	}
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJointAccount(@QueryParam("jointAccountId")String jointAccountId) {
		return Response.ok().entity(jointAccountService.getJointAccount(jointAccountId)).build();
	}
	
	@GET
	@Path("/detailsWithUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJointAccount(@QueryParam("jointAccountId")String jointAccountId,@QueryParam("userId")String userId) {
		return  Response.ok().entity(jointAccountService.getJointAccountWithUserId(jointAccountId,userId)).build();
		
	}
	
	@GET
	@Path("/getjointaccounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJointAccountsofUserId(@QueryParam("userId")String userId) {
		return  Response.ok().entity(new JointAccountSearchVO(jointAccountService.getJointAccountsofUserId(userId))).build();
		
	}
	
//	@GET
//	@Path("/search")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response searchJointAccounts(@QueryParam("keyword")String keyword) {
//		return  Response.ok().entity(jointAccountService.searchJointAccounts(keyword)).build();
//	}
	
	@GET
	@Path("/delete")
	public Response deleteJointAccount(@QueryParam("jointAccountId")String jointAccountId)
	{
		status=jointAccountService.deleteJointAccount(jointAccountId);
		if(status==1)
			return Response.ok().build();
		else 
			return Response.status(400).build();		
	}
}
