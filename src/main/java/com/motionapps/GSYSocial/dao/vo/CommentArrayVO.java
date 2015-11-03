package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.List;

public class CommentArrayVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7919311982434245490L;
	
	
	
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
