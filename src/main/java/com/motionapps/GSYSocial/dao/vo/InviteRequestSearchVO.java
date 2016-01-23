package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class InviteRequestSearchVO {
	
	private List<InviteRequestVO> inviteRequests;

	public List<InviteRequestVO> getInviteRequests() {
		return inviteRequests;
	}

	public void setInviteRequests(List<InviteRequestVO> inviteRequests) {
		this.inviteRequests = inviteRequests;
	}

	public InviteRequestSearchVO(List<InviteRequestVO> inviteRequests) {
		super();
		this.inviteRequests = inviteRequests;
	}

	


	

}
