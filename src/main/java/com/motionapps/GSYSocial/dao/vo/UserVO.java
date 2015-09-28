package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
public class UserVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2342985461916530714L;

	private String userName;
	
	private String emailId;
	
	private String password;
	
	private String dateOfBirth;
	
	private String mobileNumber;
	
	private String sessionId;
	
	private String profilePicUrl;
	
	private String jointAccountId;
	
	private String gcmDeviceId;
	
	private int followCount;

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



	public UserVO(String userName, String emailId, String password,
			String dateOfBirth, String mobileNumber, String sessionId,
			String profilePicUrl, String jointAccountId, String gcmDeviceId,
			int followCount) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.sessionId = sessionId;
		this.profilePicUrl = profilePicUrl;
		this.jointAccountId = jointAccountId;
		this.gcmDeviceId = gcmDeviceId;
		this.followCount = followCount;
	}

	public UserVO(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}



	public UserVO() {
	}


}
