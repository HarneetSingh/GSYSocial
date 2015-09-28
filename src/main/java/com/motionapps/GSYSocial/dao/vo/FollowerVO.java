package com.motionapps.GSYSocial.dao.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FollowerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4345715611490162866L;
	
	private String followId;
	private String emailId;
	private String jointAccountId;
	
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getJointAccountId() {
		return jointAccountId;
	}
	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}
	public FollowerVO()
	{}
	public FollowerVO(String followId, String emailId, String jointAccountId) {
		super();
		this.followId = followId;
		this.emailId = emailId;
		this.jointAccountId = jointAccountId;
	}
	
	

}
