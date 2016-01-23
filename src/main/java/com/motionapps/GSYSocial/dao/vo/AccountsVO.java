package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountsVO {
	
	private List<JointAccountVO> jointAccounts;
	
	private List<InviteRequestVO> inviteRequests;
	
	private List<GroupAccountVO> groupAccounts;



	public List<JointAccountVO> getJointAccounts() {
		return jointAccounts;
	}

	public List<InviteRequestVO> getInviteRequests() {
		return inviteRequests;
	}

	public List<GroupAccountVO> getGroupAccounts() {
		return groupAccounts;
	}
	
	public AccountsVO(List<JointAccountVO> jointAccounts,
			List<GroupAccountVO> groupAccounts) {
		super();
		this.jointAccounts = jointAccounts;
		this.groupAccounts = groupAccounts;
	}
	
	public AccountsVO(List<JointAccountVO> jointAccounts, List<InviteRequestVO> inviteRequests,
			List<GroupAccountVO> groupAccounts) {
		super();
		this.jointAccounts = jointAccounts;
		this.inviteRequests = inviteRequests;
		this.groupAccounts = groupAccounts;
	}


	
	
}
