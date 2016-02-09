package com.motionapps.GSYSocial.dao.vo;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;



@XmlRootElement()
public class JointAccountVO {


	private String jointAccountId;
	
	private String firstUserId;
	
	private String firstUserName;
	
	private String secondUserName;
	
	private String secondUserId;
	
	private String jointAccountName;
	
	private String momentsPics;
	
	private int followerCount;
	
	private int postCount;
	
	private Boolean userFollowing;
	
	private String profilePic;
	
	private String firstUserProfilePic;
	
	private String secondUserProfilePic;
	
	private String relationship;
	
	private String firstUserAnswer;
	
	private String secondUserAnswer;
	
	private Date createdTime;
	
	

	// privacyMode field : 0  => public, 1=> follower, 2 => me and my partner
	private int privacyMode;
		
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}



	public int getPrivacyMode() {
		return privacyMode;
	}

	public void setPrivacyMode(int privacyMode) {
		this.privacyMode = privacyMode;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getFirstUserAnswer() {
		return firstUserAnswer;
	}

	public void setFirstUserAnswer(String firstUserAnswer) {
		this.firstUserAnswer = firstUserAnswer;
	}

	public String getSecondUserAnswer() {
		return secondUserAnswer;
	}

	public void setSecondUserAnswer(String secondUserAnswer) {
		this.secondUserAnswer = secondUserAnswer;
	}

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

	public String getMomentsPics() {
		return momentsPics;
	}

	public void setMomentsPics(String momentsPics) {
		this.momentsPics = momentsPics;
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

	public JointAccountVO(String jointAccountId, String firstUserId, String firstUserName, String secondUserName,
			String secondUserId, String jointAccountName, String momentsPics, int followerCount, int postCount,
			Boolean userFollowing, String profilePic, String firstUserProfilePic, String secondUserProfilePic,
			String relationship, String firstUserAnswer, String secondUserAnswer, Date createdTime, int privacyMode) {
		super();
		this.jointAccountId = jointAccountId;
		this.firstUserId = firstUserId;
		this.firstUserName = firstUserName;
		this.secondUserName = secondUserName;
		this.secondUserId = secondUserId;
		this.jointAccountName = jointAccountName;
		this.momentsPics = momentsPics;
		this.followerCount = followerCount;
		this.postCount = postCount;
		this.userFollowing = userFollowing;
		this.profilePic = profilePic;
		this.firstUserProfilePic = firstUserProfilePic;
		this.secondUserProfilePic = secondUserProfilePic;
		this.relationship = relationship;
		this.firstUserAnswer = firstUserAnswer;
		this.secondUserAnswer = secondUserAnswer;
		this.createdTime = createdTime;
		this.privacyMode = privacyMode;
	}


	@Override
	public int hashCode() 
	{
		 return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
		            // if deriving: appendSuper(super.hashCode()).
		            append(jointAccountId).
		            toHashCode();		
	}
		@Override
		public boolean equals(Object obj) {
		      if (!(obj instanceof JointAccountVO))
		            return false;
		      	if (obj == this)
		            return true; 
		      	JointAccountVO jointAccountVO=(JointAccountVO)obj;
		      	return jointAccountId.equals(jointAccountVO.getJointAccountId());
		}





	

 
	

}
