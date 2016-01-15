package com.motionapps.GSYSocial.dao.vo;

import java.util.Date;

public class CommentVO {

	
	private String commentId;

	private String postId;
	
	private String commentText;

	private String userId;

	private String userName;
	
	private String profilePicUrl;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;



	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
	
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public CommentVO() {
	}

	public CommentVO(String commentId, String postId, String commentText, String userId, String userName,
			String profilePicUrl, Date createdTime, Date lastUpdatedTime) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.commentText = commentText;
		this.userId = userId;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}





	
}
