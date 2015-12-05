package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UserVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2342985461916530714L;
	
	private String userId;

	private String userName;

	private String oauthUid;

	private String oauthProvider;
	
	private String emailId;
	
	private String password;
	
	private String dateOfBirth;
	
	private String mobileNumber;
	
	private String sessionId;
	
	private String profilePicUrl;
	
	private String gallery;
	
	private String jointAccountId;
	
	private String gcmDeviceId;
	
	private int followCount;
	
	private boolean inviteRequestPending;

	public boolean isInviteRequestPending() {
		return inviteRequestPending;
	}

	public void setInviteRequestPending(boolean inviteRequestPending) {
		this.inviteRequestPending = inviteRequestPending;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getGallery() {
		return gallery;
	}

	public void setGallery(String gallery) {
		this.gallery = gallery;
	}

	public String getJointAccountId() {
		return jointAccountId;
	}

	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}

	public String getGcmDeviceId() {
		return gcmDeviceId;
	}

	public void setGcmDeviceId(String gcmDeviceId) {
		this.gcmDeviceId = gcmDeviceId;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOauthUid() {
		return oauthUid;
	}

	public void setOauthUid(String oauthUid) {
		this.oauthUid = oauthUid;
	}

	public String getOauthProvider() {
		return oauthProvider;
	}

	public void setOauthProvider(String oauthProvider) {
		this.oauthProvider = oauthProvider;
	}


	public UserVO() {
	}


	public UserVO(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public UserVO(String userId, String userName, String oauthUid,
			String oauthProvider, String emailId, String password,
			String dateOfBirth, String mobileNumber, String sessionId,
			String profilePicUrl, String gallery, String jointAccountId,
			String gcmDeviceId, int followCount, boolean inviteRequestPending) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.oauthUid = oauthUid;
		this.oauthProvider = oauthProvider;
		this.emailId = emailId;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.sessionId = sessionId;
		this.profilePicUrl = profilePicUrl;
		this.gallery = gallery;
		this.jointAccountId = jointAccountId;
		this.gcmDeviceId = gcmDeviceId;
		this.followCount = followCount;
		this.inviteRequestPending = inviteRequestPending;
	}

	


	

}
