package com.motionapps.GSYSocial.dao.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LikeDislikeVO {
	
	private String postId;
	
	private String userId;
	
	private Boolean type;



	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}
	
	public LikeDislikeVO() {
	}
	
	
	public LikeDislikeVO(String postId, String userId, Boolean type) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.type = type;
	}

}
