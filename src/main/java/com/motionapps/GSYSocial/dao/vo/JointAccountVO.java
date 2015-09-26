package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement()
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JointAccountVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2812375761158715640L;

	/**
	 * 
	 */

	private String jointAccountId;
	
	private String firstEmailId;
	
	private String firstUserName;
	
	private String secondUserName;
	
	private String secondEmailId;
	
	private String jointAccountName;
	
	private String jointAccountStory;

	public String getJointAccountId() {
		return jointAccountId;
	}

	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}

	public String getFirstEmailId() {
		return firstEmailId;
	}

	public void setFirstEmailId(String firstEmailId) {
		this.firstEmailId = firstEmailId;
	}

	public String getFirstUserName() {
		return firstUserName;
	}

	public void setFirstUserName(String firstUserName) {
		this.firstUserName = firstUserName;
	}

	public String getSecondUserName() {
		return secondUserName;
	}

	public void setSecondUserName(String secondUserName) {
		this.secondUserName = secondUserName;
	}

	public String getSecondEmailId() {
		return secondEmailId;
	}

	public void setSecondEmailId(String secondEmailId) {
		this.secondEmailId = secondEmailId;
	}

	public String getJointAccountName() {
		return jointAccountName;
	}

	public void setJointAccountName(String jointAccountName) {
		this.jointAccountName = jointAccountName;
	}

	public String getJointAccountStory() {
		return jointAccountStory;
	}

	public void setJointAccountStory(String jointAccountStory) {
		this.jointAccountStory = jointAccountStory;
	}
	
	public JointAccountVO() {
		// TODO Auto-generated constructor stub
	}

	public JointAccountVO(String jointAccountId, String firstEmailId,
			String firstUserName, String secondUserName, String secondEmailId,
			String jointAccountName, String jointAccountStory) {
		super();
		this.jointAccountId = jointAccountId;
		this.firstEmailId = firstEmailId;
		this.firstUserName = firstUserName;
		this.secondUserName = secondUserName;
		this.secondEmailId = secondEmailId;
		this.jointAccountName = jointAccountName;
		this.jointAccountStory = jointAccountStory;
	}
	
	

}
