package com.motionapps.GSYSocial.dao.vo;

import java.util.Set;

public class GroupMemberSearchVO{

	
	private Set<GroupMemberVO> groupMembers;

	public GroupMemberSearchVO(Set<GroupMemberVO> groupMembers) {
		super();
		this.groupMembers = groupMembers;
	}

	public Set<GroupMemberVO> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<GroupMemberVO> groupMembers) {
		this.groupMembers = groupMembers;
	}
}
