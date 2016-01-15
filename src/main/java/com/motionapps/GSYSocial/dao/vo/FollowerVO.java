package com.motionapps.GSYSocial.dao.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FollowerVO {

	
	private String followId;
	private String userId;
	private String jointAccountId;
	
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getJointAccountId() {
		return jointAccountId;
	}
	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}
	public FollowerVO()
	{}
	public FollowerVO(String followId, String userId, String jointAccountId) {
		super();
		this.followId = followId;
		this.userId = userId;
		this.jointAccountId = jointAccountId;
	}

	
	

}
