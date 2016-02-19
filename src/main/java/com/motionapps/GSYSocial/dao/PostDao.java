package com.motionapps.GSYSocial.dao;

import java.util.List;
import java.util.Set;

import javax.ws.rs.QueryParam;

import org.apache.ibatis.annotations.Param;

import com.motionapps.GSYSocial.dao.vo.LikeDislikeVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;

public interface PostDao {
	
	public Long createPost(PostVO postVO);
	
	public Long updatePost(PostVO postVO);
	
	public Long deletePost(String postId);
	
	public List<PostVO> getPostByAccount(String accountId);

	
	public List<PostVO> getPostByAccountForUserId(@Param("accountId")String accountId,@Param("userId")String userId);
	
	public List<PostVO> getPostForUser(String userId);
	
	public List<PostVO> getAllPosts();
	
	public List<PostVO> getPostOfUser(String userId);
	
	public PostVO getPostById(String postId);
	
	public Long incrementCommentCount(String postId);
	
	public Long decrementCommentCount(String postId);
	
	public Long addRating(PostVO postVO);
	
	public Long deleteRating(PostVO postVO);
	
	public Long updateRating(PostVO postVO);
	
	public Long deletePostByJointAccountId(String jointAccountId);
	
	public Long addLikeDislikeEntry( LikeDislikeVO likeDislikeVO);
	
	public Long removeLikeDislikeEntry( @Param("postId") String postId,@Param("userId")String userId);
	
	public LikeDislikeVO getLikeDislikeEntry( @Param("postId") String postId,@Param("userId")String userId);
	
	public Set<LikeDislikeVO> getAllLikeEntry();

	public Set<LikeDislikeVO> getAllLikesByPost(String postId);

	public Long deleteAllLikesByPost(String postId);
}
