package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class GroupMemberSearchVO{

	
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
