package com.motionapps.GSYSocial.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;

public interface GroupAccountDao {
	
	public Long createGroupAccount(GroupAccountVO groupAccountVO);
	
	public Long updateGroupAccount(GroupAccountVO groupAccountVO);
	
	public List<GroupAccountVO> getGroupAccounts();
	
	public GroupAccountVO getGroupAccount(String groupAccountId);
	
	public List<GroupAccountVO> searchGroupAccount(String keyword);
	
	
	public Long incrementFollowCount(String groupAccountId);
	
	public Long decrementFollowCount(String groupAccountId);
	
	public Long incrementPostCount(String groupAccountId);
	
	public Long decrementPostCount(String groupAccountId);
	
	public Long inviteGroupMember(GroupMemberVO groupMemberVO);
	
	public Long inviteAccepted(GroupMemberVO groupMemberVO);
	
	public Long removeMember(GroupMemberVO groupMemberVO);

	
	public Long inviteRejected(GroupMemberVO groupMemberVO);

	public List<GroupMemberVO> getGroupMembers(String groupAccountId);
	
	public List<GroupAccountVO> getGroupAccountsByUserId(String userId);
	
	public int getGroupAccountsByAdminId(String userId);
	
	public GroupAccountVO getGroupAccountWithUserId(@Param("groupAccountId")String groupAccountId,@Param("userId")String userId);
	
	

}
