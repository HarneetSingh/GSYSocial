package com.motionapps.GSYSocial.dao.vo;


import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;



@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InviteRequestVO {


	private String inviteRequestId;
	
	private String inviterUserId;
	
	private String inviteeUserId;
	
	private String jointAccountName;

	public String getInviteRequestId() {
		return inviteRequestId;
	}

	public void setInviteRequestId(String inviteRequestId) {
		this.inviteRequestId = inviteRequestId;
	}

	public String getInviterUserId() {
		return inviterUserId;
	}

	public void setInviterUserId(String inviterUserId) {
		this.inviterUserId = inviterUserId;
	}

	public String getInviteeUserId() {
		return inviteeUserId;
	}

	public void setInviteeUserId(String inviteeUserId) {
		this.inviteeUserId = inviteeUserId;
	}

	
	public String getJointAccountName() {
		return jointAccountName;
	}

	public void setJointAccountName(String jointAccountName) {
		this.jointAccountName = jointAccountName;
	}
	
	
	public InviteRequestVO(String inviteRequestId, String inviterUserId,
			String inviteeUserId, String jointAccountName) {
		super();
		this.inviteRequestId = inviteRequestId;
		this.inviterUserId = inviterUserId;
		this.inviteeUserId = inviteeUserId;
		this.jointAccountName = jointAccountName;
	}



	public InviteRequestVO()
	{}

	
}
