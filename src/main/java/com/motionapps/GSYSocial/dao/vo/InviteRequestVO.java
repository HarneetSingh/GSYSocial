package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;



@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InviteRequestVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1297259439187120527L;

	private String inviteRequestId;
	
	private String inviterEmailId;
	
	private String inviteeEmailId;
	
	private Boolean inviteAccepted;
	
	private Boolean inviteRejected;
	
	private String jointAccountName;

	public String getInviteRequestId() {
		return inviteRequestId;
	}

	public void setInviteRequestId(String inviteRequestId) {
		this.inviteRequestId = inviteRequestId;
	}

	public String getInviterEmailId() {
		return inviterEmailId;
	}

	public void setInviterEmailId(String inviterEmailId) {
		this.inviterEmailId = inviterEmailId;
	}

	public String getInviteeEmailId() {
		return inviteeEmailId;
	}

	public void setInviteeEmailId(String inviteeEmailId) {
		this.inviteeEmailId = inviteeEmailId;
	}

	public Boolean getInviteAccepted() {
		return inviteAccepted;
	}

	public void setInviteAccepted(Boolean inviteAccepted) {
		this.inviteAccepted = inviteAccepted;
	}

	public Boolean getInviteRejected() {
		return inviteRejected;
	}

	public void setInviteRejected(Boolean inviteRejected) {
		this.inviteRejected = inviteRejected;
	}
	
	
	public InviteRequestVO(String inviteRequestId, String inviterEmailId,
			String inviteeEmailId, Boolean inviteAccepted,
			Boolean inviteRejected, String jointAccountName) {
		super();
		this.inviteRequestId = inviteRequestId;
		this.inviterEmailId = inviterEmailId;
		this.inviteeEmailId = inviteeEmailId;
		this.inviteAccepted = inviteAccepted;
		this.inviteRejected = inviteRejected;
		this.jointAccountName = jointAccountName;
	}

	public String getJointAccountName() {
		return jointAccountName;
	}

	public void setJointAccountName(String jointAccountName) {
		this.jointAccountName = jointAccountName;
	}

	public InviteRequestVO()
	{}

	
}
