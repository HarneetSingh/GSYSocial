package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.List;

public class GroupAccountSearchVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5378135783403111129L;
	
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
