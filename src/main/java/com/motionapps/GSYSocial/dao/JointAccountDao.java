package com.motionapps.GSYSocial.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.motionapps.GSYSocial.dao.vo.JointAccountVO;

public interface JointAccountDao {
	
	public Long createJointAccount(JointAccountVO jointAccountVO);
	
	public Long updateJointAccountDetails(JointAccountVO jointAccountVO);

	public List<JointAccountVO> getJointAccounts();
	
	public JointAccountVO getJointAccount(String string);
	
	public JointAccountVO getJointAccountWithUserId(@Param("jointAccountId")String jointAccountId,@Param("userId")String userId);

	public List<JointAccountVO> searchJointAccounts(String string);
	
	public Long incrementFollowCount(String jointAccountId);
	
	public Long decrementFollowCount(String jointAccountId);
	
	public Long incrementPostCount(String jointAccountId);

	public Long decrementPostCount(String jointAccountId);
	
	public Long deleteJointAccount(String jointAccountId);


}
