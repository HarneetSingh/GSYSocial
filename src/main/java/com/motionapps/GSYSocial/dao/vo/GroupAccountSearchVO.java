package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.List;

public class GroupAccountSearchVO{
	

	
	private List<GroupAccountVO> groupAccounts;

	public List<GroupAccountVO> getGroupAccounts() {
		return groupAccounts;
	}

	public void setGroupAccounts(List<GroupAccountVO> groupAccounts) {
		this.groupAccounts = groupAccounts;
	}

	public GroupAccountSearchVO(List<GroupAccountVO> groupAccounts) {
		super();
		this.groupAccounts = groupAccounts;
	}
	
	

}
