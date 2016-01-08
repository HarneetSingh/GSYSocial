package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;


public interface UserDao {
	
	public Long createUser(UserVO user);
		
	public List<UserVO> getUsers();
	
	public UserVO getUser(String userId);
	
	public UserVO getUserByEmailId(String emailId);
	
	public UserVO getUserByOauthUid(String oauthUid);

	public List<UserVO> searchUser(String keyword);

	public String getSessionId(String emailId);
	
	public String getPassword(String userId);
	
	public Long updateSessionId(UserVO user);
	
	public Long updatePassword(ChangePasswordVO changePasswordVO);
	
	public int checkIfEmailIdAlreadyExists(String emailId);
	
	public int checkIfOauthUidAlreadyExists(String oauthUid);
	
	public Long updateUser(UserVO user);
	
	public Long updateInviteRequestStatus(UserVO userVO);
	
	public Long incrementFollowCount(String userId);
	
	public Long decrementFollowCount(String userId);
	
	public Long deleteUser(String userId);
	
	public Long clearGcmDeviceId(String gcmDeviceId);

}
