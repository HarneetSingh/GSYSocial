package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PostVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5915203332339230987L;

	private String postId;
	
	private String jointAccountId;
	
	private String fileUrl;
	
	private String fileType;
	
	private String postText;
	
	private int totalRating;
	
	private int noOfRatings;

	private int newRating;
	
	private String userName;
	
	private String profilePicUrl;
	
	private String createdTime;
	
	private String lastUpdatedTime;
	
	private String thumbNameUrl;
	
	private String commentCount;
	
	


	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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



	

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getJointAccountId() {
		return jointAccountId;
	}

	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public int getNoOfRatings() {
		return noOfRatings;
	}

	public void setNoOfRatings(int noOfRatings) {
		this.noOfRatings = noOfRatings;
	}
	
	public int getNewRating() {
		return newRating;
	}

	public void setNewRating(int newRating) {
		this.newRating = newRating;
	}


	


	public String getThumbNameUrl() {
		return thumbNameUrl;
	}

	public void setThumbNameUrl(String thumbNameUrl) {
		this.thumbNameUrl = thumbNameUrl;
	}
	
	
	public PostVO(){}

	public PostVO(String postId, String jointAccountId, String fileUrl,
			String fileType, String postText, int totalRating, int noOfRatings,
			int newRating, String userName, String profilePicUrl,
			String createdTime, String lastUpdatedTime, String thumbNameUrl,
			String commentCount) {
		super();
		this.postId = postId;
		this.jointAccountId = jointAccountId;
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.postText = postText;
		this.totalRating = totalRating;
		this.noOfRatings = noOfRatings;
		this.newRating = newRating;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.thumbNameUrl = thumbNameUrl;
		this.commentCount = commentCount;
	}






}
