package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.PostVO;

public interface PostDao {
	
	public Long createPost(PostVO postVO);
	
	public Long updatePost(PostVO postVO);
	
	public Long deletePost(String postId);
	
	public List<PostVO> getPostByJointAccount(String jointAccountId);
	
	public List<PostVO> getPostForUser(String emailId);

}
