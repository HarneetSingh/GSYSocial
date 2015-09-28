package com.motionapps.GSYSocial.dao;

import com.motionapps.GSYSocial.dao.vo.FollowerVO;

public interface FollowerDao {

	public Long followAccount(FollowerVO followerVO);
	
	public Long unfollowAccount(FollowerVO followerVO);
	
}
