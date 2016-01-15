package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class CommentArrayVO {

	
	
	private List<CommentVO> comments;

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	public CommentArrayVO(List<CommentVO> comments) {
		super();
		this.comments = comments;
	}
	
	
}
