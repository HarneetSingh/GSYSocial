package com.motionapps.GSYSocial.services;



import java.util.List;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.JointAccountDao;
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
//		UserVO firstUser=userService.getUser(inviteRequestVO.getInviterUserId());
//		logger.debug(firstUser.getUserName());
//		jointAccountVO.setFirstUserName(firstUser.getUserName());
//		jointAccountVO.setFirstUserProfilePic(firstUser.getProfilePicUrl());
		jointAccountVO.setSecondUserId(inviteRequestVO.getInviteeUserId());
//		UserVO secondUser=userService.getUser(inviteRequestVO.getInviteeUserId());
//		jointAccountVO.setSecondUserName(secondUser.getUserName());
//		jointAccountVO.setSecondUserProfilePic(secondUser.getProfilePicUrl());
		jointAccountVO.setJointAccountName(inviteRequestVO.getJointAccountName());
		jointAccountDao.createJointAccount(jointAccountVO);
		//userService.updateJointAccountDetails(jointAccountVO);
		return getJointAccount(jointAccountVO.getJointAccountId());
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
	

	public List<JointAccountVO>  getJointAccountsofUserId(String userId) {
		return jointAccountDao.getJointAccountsofUserId(userId);
	}
	
	
	public JointAccountVO getJointAccountWithUserId(String jointAccountId,String userId)
	{
		return jointAccountDao.getJointAccountWithUserId(jointAccountId, userId);
	}

	
    public JointAccountSearchVO searchJointAccounts(String keyword) {
    	
		return new JointAccountSearchVO(jointAccountDao.searchJointAccounts("%"+keyword+"%"));
	}

    public JointAccountSearchVO getJointAccounts() {
		return new JointAccountSearchVO(jointAccountDao.getJointAccounts());
	}
	
	
	
	public Long deleteJointAccount(String jointAccountId)
	{
		
		JointAccountVO mJointAccountVO=jointAccountDao.getJointAccount(jointAccountId);
		//Before deleting jointAccount Delete, all posts
		postService.deleteAllPostsByAccount(jointAccountId);
		//Delete all followers
		followerService.deleteAllFollowersByJointAccount(jointAccountId);
		mJointAccountVO.setJointAccountId("");
		userService.updateJointAccountDetails(mJointAccountVO);
		jointAccountDao.deleteJointAccount(jointAccountId);
		return (Long)1L;
		
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
