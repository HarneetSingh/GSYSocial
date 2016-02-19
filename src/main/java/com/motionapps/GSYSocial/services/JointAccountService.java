package com.motionapps.GSYSocial.services;



import java.util.Set;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.JointAccountDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class JointAccountService {
	
	@Autowired
	private JointAccountDao jointAccountDao;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private UserService userService;
	
	private FollowerVO followerVO;
	
	private static final Logger logger = 
			LoggerFactory.getLogger(JointAccountService.class);
	
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setFollowerService(FollowerService followerService) {
		this.followerService = followerService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setJointAccountDao(JointAccountDao jointAccountDao) {
		this.jointAccountDao = jointAccountDao;
	}
	
	public JointAccountVO createJointAccount(InviteRequestVO inviteRequestVO)
	{
		JointAccountVO jointAccountVO=new JointAccountVO();
		jointAccountVO.setJointAccountId(UUID.randomUUID().toString());
		logger.debug(inviteRequestVO.getInviterUserId());
		jointAccountVO.setFirstUserId(inviteRequestVO.getInviterUserId());
		jointAccountVO.setSecondUserId(inviteRequestVO.getInviteeUserId());
		jointAccountVO.setJointAccountName(inviteRequestVO.getJointAccountName());
		jointAccountDao.createJointAccount(jointAccountVO);
		jointAccountVO=getJointAccount(jointAccountVO.getJointAccountId());
		//Automatic adding First User & Second User as followers
		followerVO=new FollowerVO();
		followerVO.setAccountId(jointAccountVO.getJointAccountId());
		followerVO.setUserId(jointAccountVO.getFirstUserId());
		followerVO.setAccountType(0);
		followerService.followAccount(followerVO);
		followerVO.setAccountId(jointAccountVO.getJointAccountId());
		followerVO.setUserId(jointAccountVO.getSecondUserId());
		followerVO.setAccountType(0);
		followerService.followAccount(followerVO);
		return jointAccountVO;
	}
	
	
	public Long updateJointAccountDetails(JointAccountVO jointAccountVO)
	{
		return jointAccountDao.updateJointAccountDetails(jointAccountVO);
		
	}

	public JointAccountVO  getJointAccount(String jointAccountId) {
		JointAccountVO jointAccountVO=jointAccountDao.getJointAccount(jointAccountId);
		UserVO userVO=userService.getUser(jointAccountVO.getFirstUserId());
		jointAccountVO.setFirstUserName(userVO.getUserName());
		jointAccountVO.setFirstUserProfilePic(userVO.getProfilePicUrl());
		userVO=userService.getUser(jointAccountVO.getSecondUserId());
		jointAccountVO.setSecondUserName(userVO.getUserName());
		jointAccountVO.setSecondUserProfilePic(userVO.getProfilePicUrl());
		return jointAccountVO;
	}
	

	public Set<JointAccountVO>  getJointAccountsofUserId(String userId) {
		return jointAccountDao.getJointAccountsofUserId(userId);
	}
	
	
	public JointAccountVO getJointAccountWithUserId(String jointAccountId,String userId)
	{
		JointAccountVO jointAccountVO= jointAccountDao.getJointAccountWithUserId(jointAccountId, userId);
		if(jointAccountVO.getUserFollowing()==0)
		{
			int status=followerService.isFollowRequestPending(jointAccountId, userId);
			System.out.println("status"+status);

			if(status!=0)
				jointAccountVO.setUserFollowing(2);
		}
		UserVO userVO=userService.getUser(jointAccountVO.getFirstUserId());
		jointAccountVO.setFirstUserName(userVO.getUserName());
		jointAccountVO.setFirstUserProfilePic(userVO.getProfilePicUrl());
		userVO=userService.getUser(jointAccountVO.getSecondUserId());
		jointAccountVO.setSecondUserName(userVO.getUserName());
		jointAccountVO.setSecondUserProfilePic(userVO.getProfilePicUrl());
		return jointAccountVO;
	}

	public Set<JointAccountVO> getJointAccountsFollowedByUser(String userId)
	{
		return jointAccountDao.getJointAccountsFollowedByUser(userId);
	}
	
    public Set<JointAccountVO> searchJointAccounts(String keyword) {
    	
		return jointAccountDao.searchJointAccounts("%"+keyword+"%");
	}

    public JointAccountSearchVO getJointAccounts() {
		return new JointAccountSearchVO(jointAccountDao.getJointAccounts());
	}
	
	
	
	public Long deleteJointAccount(String jointAccountId)
	{
		
		//Before deleting jointAccount Delete, all posts
		postService.deleteAllPostsByAccount(jointAccountId);
		//Delete all followers
		followerService.deleteAllFollowersByAccount(jointAccountId);
		followerService.deteteAllFollowerRequestByAccountId(jointAccountId);
		jointAccountDao.deleteJointAccount(jointAccountId);
		return (Long)1L;
		
	}
	
	public Long deleteJointAccountOfUser(String userId)
	{
		Set<JointAccountVO> jointAccountVOs=getJointAccountsofUserId(userId);
		for(JointAccountVO jointAccountVO:jointAccountVOs)
		{
			deleteJointAccount(jointAccountVO.getJointAccountId());
		}
		return 1L;
	}
	
	public int checkIfJointAccountAlreadyExists(String inviteeUserId,String inviterUserId)
	{
		return jointAccountDao.checkIfJointAccountAlreadyExists(inviteeUserId, inviterUserId);
	}
	
	public Long incrementPostCount(String jointAccountId)
	{
		return jointAccountDao.incrementPostCount(jointAccountId);
	}

	public Long decrementPostCount(String jointAccountId) {

		return jointAccountDao.decrementPostCount(jointAccountId);
	}
	
	
	public Long incrementFollowCount(String jointAccountId)
	{
		return jointAccountDao.incrementFollowCount(jointAccountId);
	}
	
	public Long decrementFollowCount(String jointAccountId)
	{
		return jointAccountDao.decrementFollowCount(jointAccountId);
	}
	

}
