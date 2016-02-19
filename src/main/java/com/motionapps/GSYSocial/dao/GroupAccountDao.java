package com.motionapps.GSYSocial.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;

public interface GroupAccountDao {
	
	public Long createGroupAccount(GroupAccountVO groupAccountVO);
	
	public Long updateGroupAccount(GroupAccountVO groupAccountVO);
	
	public Set<GroupAccountVO> getGroupAccounts();
	
	public GroupAccountVO getGroupAccount(String groupAccountId);
	
	public Set<GroupAccountVO> searchGroupAccount(String keyword);
	
	
	public Long incrementFollowCount(String groupAccountId);
	
	public Long decrementFollowCount(String groupAccountId);
	
	public Long incrementPostCount(String groupAccountId);
	
	public Long decrementPostCount(String groupAccountId);
	
	public Long inviteGroupMember(GroupMemberVO groupMemberVO);
	
	public Long inviteAccepted(GroupMemberVO groupMemberVO);
	
	public Long removeMember(GroupMemberVO groupMemberVO);
	
	public Long deleteGroupAccount(String groupAccountId);

	
	public Long inviteRejected(GroupMemberVO groupMemberVO);

	public Set<GroupMemberVO> getGroupMembers(String groupAccountId);
	
	public Set<GroupMemberVO> getAllGroupMembers(String groupAccountId);

	
	public Set<GroupAccountVO> getGroupAccountsByUserId(String userId);
	
	public Set<GroupAccountVO> getGroupAccountsByAdminId(String userId);
	
	public GroupAccountVO getGroupAccountWithUserId(@Param("groupAccountId")String groupAccountId,@Param("userId")String userId);
	
	public Set<GroupAccountVO> getGroupAccountsFollowedByUser(String userId);
	
	public Long removeGroupMemberByUserId(String userId);
	

}
