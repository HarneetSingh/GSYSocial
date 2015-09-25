package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.JointAccountVO;

public interface JointAccountDao {
	
	public Long createJointAccount(JointAccountVO jointAccountVO);
	
	public Long updateJointAccountDetails(JointAccountVO jointAccountVO);

	public List<JointAccountVO> getJointAccounts();
	
	public JointAccountVO getJointAccount(String string);

	public List<JointAccountVO> searchJointAccounts(String string);

}
