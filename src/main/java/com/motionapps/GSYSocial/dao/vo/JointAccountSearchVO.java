package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JointAccountSearchVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6989280001788008602L;
	
	private List<JointAccountVO> jointAccounts;
	
	public  JointAccountSearchVO() {
		
	}
	public JointAccountSearchVO(List<JointAccountVO> jointAccounts) {
		super();
		this.jointAccounts = jointAccounts;
	}

	public List<JointAccountVO> getJointAccounts() {
		return jointAccounts;
	}

	public void setJointAccounts(List<JointAccountVO> jointAccounts) {
		this.jointAccounts = jointAccounts;
	}
}
