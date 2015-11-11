package com.motionapps.GSYSocial.services;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.InviteRequestDao;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class InviteRequestService {

	
	@Autowired
	private InviteRequestDao inviteRequestDao;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private JointAccountService jointAccountService;
	
	
	private JointAccountVO jointAccountVO;


	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}



	public void setInviteRequestDao(InviteRequestDao inviteRequestDao) {
		this.inviteRequestDao = inviteRequestDao;
	}
	
	public InviteRequestVO inviteUser(InviteRequestVO inviteRequestVO)
	{
		String inviteRequestId=UUID.randomUUID().toString();
		inviteRequestVO.setInviteRequestId(inviteRequestId);
		inviteRequestDao.inviteUser(inviteRequestVO);
		
		//Setting the invite_request_pending flag in user table to true
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.TRUE);
		userService.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateUserDetails(userVO);
		
		return inviteRequestVO;

	}
	
	public JointAccountVO inviteAccepted(InviteRequestVO inviteRequestVO)
	{
//		inviteRequestVO.setInviteAccepted(Boolean.TRUE);
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userService.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateUserDetails(userVO);
		
		Long result=inviteRequestDao.inviteDeleted(inviteRequestVO);		

		if(result==1){
			//Setting the invite_request_pending flag in user table to false
			//inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
			jointAccountVO=jointAccountService.createJointAccount(inviteRequestVO);
			return jointAccountVO;
			
		}else
			{
			return jointAccountVO;
//			ErrorVO errorVO=new ErrorVO("No Pending Request Found");
//			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}

	}
	
	public Long inviteRejected(InviteRequestVO inviteRequestVO)
	{
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());

		//Setting the invite_request_pending flag in user table to false
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userService.updateUserDetails(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateUserDetails(userVO);
		return inviteRequestDao.inviteDeleted(inviteRequestVO);
	}
	
	public InviteRequestVO pendingRequests(String userId)
	{
		return inviteRequestDao.getInviteRequest(userId);
	}
}
