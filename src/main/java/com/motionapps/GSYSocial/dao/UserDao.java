package com.motionapps.GSYSocial.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

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
	
	public String getPassword(String emailId);
	
	public Long updateSessionId(UserVO user);
	
	public Long updatePassword(ChangePasswordVO changePasswordVO);
	
	public int checkIfEmailIdAlreadyExists(String emailId);
	
	public int checkIfOauthUidAlreadyExists(String oauthUid);
	
	public int checkIfMobileNoAlreadyExists(String mobileNumber);
	
	public Long updateUser(UserVO user);
		
	public Long incrementFollowCount(String userId);
	
	public Long decrementFollowCount(String userId);
	
	public Long deleteUser(String userId);
	
	public Long clearGcmDeviceId(String gcmDeviceId);
	
	public Long verifyEmailAddress(@Param("userId")String userId, @Param("emailId") String emailId );

	public Long setTempPassword(UserVO userVO);
	
	public String getTempPassword(String emailId);

}
