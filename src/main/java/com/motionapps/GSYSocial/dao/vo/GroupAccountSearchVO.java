package com.motionapps.GSYSocial.dao.vo;

import java.util.Set;

public class GroupAccountSearchVO{
	

	
	private Set<GroupAccountVO> groupAccounts;

	public Set<GroupAccountVO> getGroupAccounts() {
		return groupAccounts;
	}

	public void setGroupAccounts(Set<GroupAccountVO> groupAccounts) {
		this.groupAccounts = groupAccounts;
	}

	public GroupAccountSearchVO(Set<GroupAccountVO> groupAccounts) {
		super();
		this.groupAccounts = groupAccounts;
	}
	
	

}
