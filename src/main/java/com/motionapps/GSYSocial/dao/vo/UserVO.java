package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserVO implements Serializable{
	
	private String userName;
	
	private String emailId;
	
	private String password;
	
	private String dateOfBirth;
	
	private String mobileNumber;
	
	private String sessionId;
	
	private String profilePicUrl;
	
	private String jointAccountId;

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

	public UserVO(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public UserVO(String userName, String emailId, String password,
			String dateOfBirth, String mobileNumber, String sessionId,
			String profilePicUrl, String jointAccountId) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
		this.sessionId = sessionId;
		this.profilePicUrl = profilePicUrl;
		this.jointAccountId = jointAccountId;
	}

	public UserVO() {
	}


}
