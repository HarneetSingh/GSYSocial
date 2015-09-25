package com.motionapps.GSYSocial.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.JointAccountDao;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

@Controller
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
		jointAccountVO.setFirstEmailId(inviteRequestVO.getInviterEmailId());
		jointAccountVO.setSecondEmailId(inviteRequestVO.getInviteeEmailId());
		jointAccountVO.setJointAccountName(inviteRequestVO.getJointAccountName());
		jointAccountDao.createJointAccount(jointAccountVO);
		return userController.updateJointAccountDetails(jointAccountVO);
	}

}
