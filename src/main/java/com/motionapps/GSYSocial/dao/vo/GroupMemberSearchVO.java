package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.List;

public class GroupMemberSearchVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6809269180405535513L;
	
	private List<GroupMemberVO> groupMembers;

	public GroupMemberSearchVO(List<GroupMemberVO> groupMembers) {
		super();
		this.groupMembers = groupMembers;
	}

	public List<GroupMemberVO> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMemberVO> groupMembers) {
		this.groupMembers = groupMembers;
	}
}
