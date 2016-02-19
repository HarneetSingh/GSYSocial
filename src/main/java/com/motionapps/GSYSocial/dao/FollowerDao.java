package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public interface FollowerDao {

	public Long followAccount(FollowerVO followerVO);
	
	public Long unfollowAccount(FollowerVO followerVO);
	
	public FollowerVO getFollowRequest(String followerRequestId);
	
	public Long sendFollowRequest(FollowerVO followerVO);

	public Long removeFollowRequest(String followerRequestId);
		
	public List<UserVO> getAccountFollowers(String jointAccountId);
			
	public List<FollowerVO> getFollowersByAccountId(String accountId);
	
	public List<FollowerVO> getFollowerVOByUserId(String userId);
	
	public List<FollowerVO> getAllFollowers();
	
	public List<FollowerVO> getFollowRequestByUser(String userId);
	
	public List<FollowerVO> getFollowRequestByAcc(String accountId);
	
	public int isFollowRequestPending(FollowerVO followerVO);

}
