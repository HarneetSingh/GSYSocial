package com.motionapps.GSYSocial.dao.vo;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountsVO {
	
	private Set<JointAccountVO> jointAccounts;
	
	private Set<InviteRequestVO> inviteRequests;
	
	private Set<GroupAccountVO> groupAccounts;



	public Set<JointAccountVO> getJointAccounts() {
		return jointAccounts;
	}

	public Set<InviteRequestVO> getInviteRequests() {
		return inviteRequests;
	}

	public Set<GroupAccountVO> getGroupAccounts() {
		return groupAccounts;
	}
	
	public AccountsVO(Set<JointAccountVO> jointAccounts,
			Set<GroupAccountVO> groupAccounts) {
		super();
		this.jointAccounts = jointAccounts;
		this.groupAccounts = groupAccounts;
	}
	
	public AccountsVO(Set<JointAccountVO> jointAccounts, Set<InviteRequestVO> inviteRequests,
			Set<GroupAccountVO> groupAccounts) {
		super();
		this.jointAccounts = jointAccounts;
		this.inviteRequests = inviteRequests;
		this.groupAccounts = groupAccounts;
	}


	
	
}
