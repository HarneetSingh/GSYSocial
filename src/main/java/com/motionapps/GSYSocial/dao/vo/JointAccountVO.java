package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement()
public class JointAccountVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2812375761158715640L;

	/**
	 * 
	 */

	private String jointAccountId;
	
	private String firstUserId;
	
	private String firstUserName;
	
	private String secondUserName;
	
	private String secondUserId;
	
	private String jointAccountName;
	
	private String jointAccountStory;
	
	private int followerCount;
	
	private int postCount;
	
	private Boolean userFollowing;
	
	private String profilePic;
	
	private String firstUserProfilePic;
	
	private String secondUserProfilePic;

	


	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Boolean getUserFollowing() {
		return userFollowing;
	}

	public void setUserFollowing(Boolean userFollowing) {
		this.userFollowing = userFollowing;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public String getJointAccountId() {
		return jointAccountId;
	}

	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}

	public String getFirstUserId() {
		return firstUserId;
	}

	public void setFirstUserId(String firstUserId) {
		this.firstUserId = firstUserId;
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

	public String getSecondUserId() {
		return secondUserId;
	}

	public void setSecondUserId(String secondUserId) {
		this.secondUserId = secondUserId;
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
	
	public String getFirstUserProfilePic() {
		return firstUserProfilePic;
	}

	public void setFirstUserProfilePic(String firstUserProfilePic) {
		this.firstUserProfilePic = firstUserProfilePic;
	}

	public String getSecondUserProfilePic() {
		return secondUserProfilePic;
	}

	public void setSecondUserProfilePic(String secondUserProfilePic) {
		this.secondUserProfilePic = secondUserProfilePic;
	}

	public JointAccountVO() {
	}

	public JointAccountVO(String jointAccountId, String firstUserId,
			String firstUserName, String secondUserName, String secondUserId,
			String jointAccountName, String jointAccountStory,
			int followerCount, int postCount, Boolean userFollowing,
			String profilePic, String firstUserProfilePic,
			String secondUserProfilePic) {
		super();
		this.jointAccountId = jointAccountId;
		this.firstUserId = firstUserId;
		this.firstUserName = firstUserName;
		this.secondUserName = secondUserName;
		this.secondUserId = secondUserId;
		this.jointAccountName = jointAccountName;
		this.jointAccountStory = jointAccountStory;
		this.followerCount = followerCount;
		this.postCount = postCount;
		this.userFollowing = userFollowing;
		this.profilePic = profilePic;
		this.firstUserProfilePic = firstUserProfilePic;
		this.secondUserProfilePic = secondUserProfilePic;
	}


	

 
	

}
