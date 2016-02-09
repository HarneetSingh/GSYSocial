package com.motionapps.GSYSocial.dao.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class GroupAccountVO {


	private String groupAccountId;

	private String groupAdminId;

	private String groupAdminName;

	private String groupAdminProfilePic;

	private String groupAccountName;

	private int privacyMode;

	private String profilePic;

	private String relationship;

	private Boolean userFollowing;

	private Date createdTime;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Boolean getUserFollowing() {
		return userFollowing;
	}

	public void setUserFollowing(Boolean userFollowing) {
		this.userFollowing = userFollowing;
	}
	private String answer;

	private String momentsPics;

	private int followerCount;

	private int postCount;


	public int getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public String getGroupAccountName() {
		return groupAccountName;
	}

	public void setGroupAccountName(String groupAccountName) {
		this.groupAccountName = groupAccountName;
	}

	public String getGroupAccountId() {
		return groupAccountId;
	}

	public void setGroupAccountId(String groupAccountId) {
		this.groupAccountId = groupAccountId;
	}

	public String getGroupAdminId() {
		return groupAdminId;
	}

	public void setGroupAdminId(String groupAdminId) {
		this.groupAdminId = groupAdminId;
	}

	public int getPrivacyMode() {
		return privacyMode;
	}

	public void setPrivacyMode(int privacyMode) {
		this.privacyMode = privacyMode;
	}

	public String getMomentsPics() {
		return momentsPics;
	}

	public void setMomentsPics(String momentsPics) {
		this.momentsPics = momentsPics;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getGroupAdminName() {
		return groupAdminName;
	}


	public void setGroupAdminName(String groupAdminName) {
		this.groupAdminName = groupAdminName;
	}

	public String getGroupAdminProfilePic() {
		return groupAdminProfilePic;
	}

	public void setGroupAdminProfilePic(String groupAdminProfilePic) {
		this.groupAdminProfilePic = groupAdminProfilePic;
	}

	public GroupAccountVO()
	{}

	public GroupAccountVO(String groupAccountId, String groupAdminId, String groupAdminName,
			String groupAdminProfilePic, String groupAccountName, int privacyMode, String profilePic,
			String relationship, Boolean userFollowing, Date createdTime, String answer, String momentsPics,
			int followerCount, int postCount) {
		super();
		this.groupAccountId = groupAccountId;
		this.groupAdminId = groupAdminId;
		this.groupAdminName = groupAdminName;
		this.groupAdminProfilePic = groupAdminProfilePic;
		this.groupAccountName = groupAccountName;
		this.privacyMode = privacyMode;
		this.profilePic = profilePic;
		this.relationship = relationship;
		this.userFollowing = userFollowing;
		this.createdTime = createdTime;
		this.answer = answer;
		this.momentsPics = momentsPics;
		this.followerCount = followerCount;
		this.postCount = postCount;
	}

	@Override
	public int hashCode() 
	{
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(groupAccountId).
				toHashCode();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GroupAccountVO))
			return false;
		if (obj == this)
			return true; 
		GroupAccountVO groupAccountVO=(GroupAccountVO)obj;
		return groupAccountId.equals(groupAccountVO.getGroupAccountId());
	}




}
