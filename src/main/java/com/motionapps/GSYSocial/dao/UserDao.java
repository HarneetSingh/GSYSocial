package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;


public interface UserDao {
	
	public Long createUser(UserVO user);
		
	public List<UserVO> getUsers();
	
	public UserVO getUser(String emailId);
	
	public List<UserVO> searchUser(String keyword);

	public String getSessionId(String emailId);
	
	public String getPassword(String emailId);
	
	public Long updateSessionId(UserVO user);
	
	public Long updatePassword(ChangePasswordVO changePasswordVO);
	
	public int checkIfEmailIdAlreadyExists(String emailId);
	
	public int checkIfUserNameAlreadyExists(String userName);
	
	public Long updateUser(UserVO user);
}
