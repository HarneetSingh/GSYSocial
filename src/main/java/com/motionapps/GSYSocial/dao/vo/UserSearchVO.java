package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class UserSearchVO {
	private List<UserVO> users;

	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public UserSearchVO() {
	}
	
	public UserSearchVO(List<UserVO> users) {
		super();
		this.users = users;
	}
	
}
