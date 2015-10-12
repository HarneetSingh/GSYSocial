package com.motionapps.GSYSocial.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public interface FollowerDao {

	public Long followAccount(FollowerVO followerVO);
	
	public Long unfollowAccount(FollowerVO followerVO);
	
	public List<UserVO> getJointAccountFollowers(String jointAccountId);
	
	public List<JointAccountVO> getJointAccountsFollowedByUser(String userId);
		
}
