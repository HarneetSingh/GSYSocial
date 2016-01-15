package com.motionapps.GSYSocial.dao.vo;

public class GroupMemberVO {
	


	private String groupAccountId;
	
	private String userId;
	
	private int notificationMode;
	
	private String userName;
	
	private String profilePicUrl;
	
	private int memberStatus;
	
	
	public int getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getGroupAccountId() {
		return groupAccountId;
	}

	public void setGroupAccountId(String groupAccountId) {
		this.groupAccountId = groupAccountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNotificationMode() {
		return notificationMode;
	}

	public void setNotificationMode(int notificationMode) {
		this.notificationMode = notificationMode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	
	

}
