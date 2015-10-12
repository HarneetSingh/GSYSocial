package com.motionapps.GSYSocial.controller;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.JointAccountDao;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

@Controller
@Path("/account")
public class JointAccountController {
	
	
	@Autowired
	public JointAccountDao jointAccountDao;
	@Autowired
	private UserController userController;

	public void setJointAccountDao(JointAccountDao jointAccountDao) {
		this.jointAccountDao = jointAccountDao;
	}
	
	public void setUserController(UserController userController) {
		this.userController = userController;
	}
	
	public Long createJointAccount(InviteRequestVO inviteRequestVO)
	{
		JointAccountVO jointAccountVO=new JointAccountVO();
		jointAccountVO.setJointAccountId(UUID.randomUUID().toString());
		System.out.println(inviteRequestVO.getInviterUserId());
		jointAccountVO.setFirstUserId(inviteRequestVO.getInviterUserId());
		UserVO firstUser=userController.getUser(inviteRequestVO.getInviterUserId());
		System.out.println(firstUser.getUserName());
		jointAccountVO.setFirstUserName(firstUser.getUserName());
		jointAccountVO.setSecondUserId(inviteRequestVO.getInviteeUserId());
		UserVO secondUser=userController.getUser(inviteRequestVO.getInviteeUserId());
		jointAccountVO.setSecondUserName(secondUser.getUserName());
		jointAccountVO.setJointAccountName(inviteRequestVO.getJointAccountName());
		jointAccountDao.createJointAccount(jointAccountVO);
		return userController.updateJointAccountDetails(jointAccountVO);
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJointAccountDetails(JointAccountVO jointAccountVO)
	{
		jointAccountDao.updateJointAccountDetails(jointAccountVO);
		return Response.ok().build();
	}
	
	/************************************ READ ************************************/
	/**
	 * Returns all resources (questions) from the database
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JointAccountVO> getJointAccounts() {
		return jointAccountDao.getJointAccounts();
	}
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public JointAccountVO getJointAccount(@QueryParam("jointAccountId")String jointAccountId) {
		return jointAccountDao.getJointAccount(jointAccountId);
	}
	
	@GET
	@Path("/detailsWithUserId")
	@Produces(MediaType.APPLICATION_JSON)
	public JointAccountVO getJointAccount(@QueryParam("jointAccountId")String jointAccountId,@QueryParam("userId")String userId) {
		return jointAccountDao.getJointAccountWithUserId(jointAccountId,userId);
		
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public JointAccountSearchVO searchJointAccounts(@QueryParam("keyword")String keyword) {
		return new JointAccountSearchVO(jointAccountDao.searchJointAccounts("%"+keyword+"%"));
	}

}
