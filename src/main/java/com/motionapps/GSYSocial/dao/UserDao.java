package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.entities.User;


public interface UserDao {
	
	public Long createUser(User user);
		
	public List<User> getUsers();
	
	public String getSessionId(String emailId);
	
	public String getPassword(String emailId);
	
	public Long updateSessionId(User user);
}
