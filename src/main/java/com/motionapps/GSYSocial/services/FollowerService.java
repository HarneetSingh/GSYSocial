package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.FollowerDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;

public class FollowerService {
	
	@Autowired
	private FollowerDao followerDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JointAccountService jointAccountService;
	



	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}

	


	public void setUserService(UserService userService) {
		this.userService = userService;
	}




	public void setFollowerDao(FollowerDao followerDao) {
		this.followerDao = followerDao;
	}
	
	
	public Long followAccount(FollowerVO followerVO)
	{
		followerVO.setFollowId(UUID.randomUUID().toString());
		followerDao.followAccount(followerVO);
		jointAccountService.incrementFollowCount(followerVO.getJointAccountId());
		return userService.incrementFollowCount(followerVO.getUserId());
		 
	}
	
	public Long unfollowAccount(FollowerVO followerVO)
	{
		followerDao.unfollowAccount(followerVO);
		jointAccountService.decrementFollowCount(followerVO.getJointAccountId());
		return userService.decrementFollowCount(followerVO.getUserId());
		 
	}
	
	public UserSearchVO getJointAccountFollowers(String jointAccountId)
	{
		return new UserSearchVO(followerDao.getJointAccountFollowers(jointAccountId));
	}
	
	public JointAccountSearchVO getJointAccountsFollowedByUser(String userId)
	{
		return new JointAccountSearchVO(followerDao.getJointAccountsFollowedByUser(userId));
	}
	
	public Long deleteAllFollowersByJointAccount(String jointAccountId)
	{
		List<FollowerVO> followersVO=followerDao.getFollowersVOByJointAccount(jointAccountId);
		for(FollowerVO tempFollowerVO:followersVO)
		{
			followerDao.unfollowAccount(tempFollowerVO);
			userService.decrementFollowCount(tempFollowerVO.getUserId());
		}
		
		return 1L;
	}

}
