package com.motionapps.GSYSocial.dao.vo;



import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FollowerVO {

	
	private String followId;
	private String userId;
	private String accountId;
	private int accountType;
	
	
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


	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}


	public FollowerVO()
	{}
	
	public FollowerVO(String followId, String userId, String accountId, int accountType) {
		super();
		this.followId = followId;
		this.userId = userId;
		this.accountId = accountId;
		this.accountType = accountType;
	}
	

}
