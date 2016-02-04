package com.motionapps.GSYSocial.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.UserDao;
import com.motionapps.GSYSocial.dao.vo.AccountsVO;
import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.ErrorVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;



public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	private UserVO userVO;
	
	@Autowired
	private JointAccountService jointAccountService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private InviteRequestService inviteRequestService;
	
	@Autowired
	private GroupAccountService groupAccountService;
	
	@Autowired
	private EmailService emailService;
	

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}


	public void setInviteRequestService(InviteRequestService inviteRequestService) {
		this.inviteRequestService = inviteRequestService;
	}


	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}


	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}


	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}


	public void setFollowerService(FollowerService followerService) {
		this.followerService = followerService;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	public Object normalregisteration(UserVO user) {
		
		if((user.getEmailId()==null)||(user.getEmailId().equals(""))||(user.getPassword().equals(""))||(user.getPassword()==null))
			return null;
		int temp=userDao.checkIfEmailIdAlreadyExists(user.getEmailId());
		if(temp>=1)
		{

			return new ErrorVO(401, "Email Id Already Exists");
		}
		
		String userId=UUID.randomUUID().toString();
		user.setSessionId(userId);
		user.setUserId(userId);
		
		Long abc=userDao.createUser(user);
		if(abc==1)
		{
			user.setPassword(null);
			registrationEmail(userId, user.getEmailId(),user.getUserName());
			return user;
		}
		else
		{
			return null;
		}	
		
	}	
	
	public UserVO oauthregisteration(UserVO user) {
		
	//	System.out.println("Inside oauthregisteration");

		if((user.getOauthProvider()==null)||(user.getOauthProvider().equals(""))||(user.getOauthUid().equals(""))||(user.getOauthUid()==null))
			return userVO;
		int temp=userDao.checkIfOauthUidAlreadyExists(user.getOauthUid());
		if(temp>=1)
		{
//			System.out.println("Inside IF");
			String gcmDeviceId=user.getGcmDeviceId();
			UserVO response = userDao.getUserByOauthUid(user.getOauthUid());
//			System.out.println("Inside "+ response.getUserId());
			//Updating the gcm device id
			if(gcmDeviceId!=null)
			{
//				System.out.println("Inside IF gcmDeviceId");
				UserVO tempUser=new UserVO();
				tempUser.setUserId(response.getUserId());
				tempUser.setGcmDeviceId(gcmDeviceId);
				userDao.updateUser(tempUser);
				response.setGcmDeviceId(gcmDeviceId);
			}
			return response;
		}
		
		String userId=UUID.randomUUID().toString();
		user.setSessionId(userId);
		user.setUserId(userId);
		
		Long abc=userDao.createUser(user);
		if(abc==1)
		{
			user.setPassword(null);
			return user;
		}
		else
		{
			return userVO;
		}	
		
	}	
	
	public Long updateJointAccountDetails(JointAccountVO jointAccountVO)
	{
		UserVO userVO=new UserVO();
		userVO.setUserId(jointAccountVO.getFirstUserId());
		userVO.setJointAccountId(jointAccountVO.getJointAccountId());
		userDao.updateUser(userVO);
		userVO.setUserId(jointAccountVO.getSecondUserId());
		userDao.updateUser(userVO);
		return (long)1;
	}
	
	public Long deleteUser(String userId) {

		//delete follower
		followerService.deleteAllFollowersByUserId(userId);
		
		//delete invite request
		
		inviteRequestService.deleteInviteRequest(userId);
		
		//delete comment by user
		
		commentService.deleteAllCommentsByUserId(userId);
		
		//deleting ratings
		ratingService.deleteRatingsByUserId(userId);
		UserVO userVO=getUser(userId);
		
		//deleting joint acc will delete all posts automatically
		if(userVO.getJointAccountId()!=null&userVO.getJointAccountId()!="")
		{
			
			jointAccountService.deleteJointAccount(userVO.getJointAccountId());
		}
		return userDao.deleteUser(userId);
	}
	
	public List<UserVO> getUsers() {
		return userDao.getUsers();
	}
	
	
	
	public List<UserVO>  searchUser(String keyword) {
		return userDao.searchUser("%"+keyword+"%");
	}
	
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
	}
	
	
	public Long incrementFollowCount(String emailId)
	{
		userDao.incrementFollowCount(emailId);
		return (long)1;
	}
	
	public Long decrementFollowCount(String emailId)
	{
		userDao.decrementFollowCount(emailId);
		return (long)1;
	}
	
	public Long updateUserDetails(UserVO user) {
		
		return userDao.updateUser(user);

		
	}	
	public Long updateInviteRequestStatus(UserVO userVO) {
		
		return userDao.updateInviteRequestStatus(userVO);
		
	}
	
	public Object loginUser(UserVO user) {
		String emailId=user.getEmailId();
		String password =userDao.getPassword(emailId);


		String gcmDeviceId=user.getGcmDeviceId();
		if(password!=null)
		{	
			if(password.equals(user.getPassword()))
			{

				user=userDao.getUserByEmailId(emailId);

				if(user.isEmailVerified())
				{
					//Updating the gcm device id
					if(gcmDeviceId!=null)
					{
						clearGcmDeviceId(gcmDeviceId);	
						UserVO  tempUser =new UserVO();
						tempUser.setUserId(user.getUserId());
						tempUser.setGcmDeviceId(gcmDeviceId);
						userDao.updateUser(tempUser);
						user.setGcmDeviceId(gcmDeviceId);
					}

					return user;
				}
				else 
				{
					return new ErrorVO(401,"Please verify your account ");
				}
			}
			else if(user.getPassword().equals(userDao.getTempPassword(emailId)))
			{
				return new ErrorVO(403,"Trying to login with temporary password. Please redirect to change password page");
			}
			else
			{
				return new ErrorVO(401,"Invalid Login Creadentials");
			}	
		}
		else
		{
			return new ErrorVO(401,"Invalid Login Creadentials");

		}	
	}
	
	public Long clearGcmDeviceId(String gcmDeviceId)
	{
		return userDao.clearGcmDeviceId(gcmDeviceId);
	}
	
	public Long changePassword(ChangePasswordVO changePasswordVO)
	{
		
	
		if(changePasswordVO.getOldPassword().equals(userDao.getPassword(changePasswordVO.getEmailId())))
		{
			userDao.updatePassword(changePasswordVO);
			return 1L;
		}
		else if(changePasswordVO.getOldPassword().equals(userDao.getTempPassword(changePasswordVO.getEmailId())))
		{
			userDao.updatePassword(changePasswordVO);
			return 1L;
		}
		else{
			//ErrorVO errorVO=new ErrorVO("Authentication Failed");
			return 0L;
		}

	}
	
	public AccountsVO getAccountsDetails(String userId)
	{
		List<JointAccountVO> jointAccounts =jointAccountService.getJointAccountsofUserId(userId);
		List<GroupAccountVO> groupAccounts=groupAccountService.getGroupAccountsByUserId(userId);
		List<InviteRequestVO> inviteRequests=inviteRequestService.getInviteRequests(userId);
		return new AccountsVO(jointAccounts, inviteRequests, groupAccounts);
	}

	public AccountsVO searchUsersAccount(String keyword)
	{
		List<UserVO> userVOs=searchUser(keyword);
		List<JointAccountVO> jointAccounts=new ArrayList<JointAccountVO>();
		List<GroupAccountVO> groupAccounts=new ArrayList<GroupAccountVO>();
		for(UserVO userVO:userVOs)
		{
		String userId=userVO.getUserId();
		jointAccounts.addAll(jointAccountService.getJointAccountsofUserId(userId));
		groupAccounts.addAll(groupAccountService.getGroupAccountsByUserId(userId));
		}
		return new AccountsVO(jointAccounts, groupAccounts);
	}

	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
	}
	
	
	public void registrationEmail(String userId,String emailId,String name)
	{
		String subject="Intact You Verification Email";
		String textBody="Dear "+name
				+ "\n\nWe can't wait for you to join us!  We have received a request to authorize this email address to continue with Intactyou. "
				+"\n\nPlease Click Below To verify your account:\n"
				+ "\nhttp://api.intactyou.com/GFYSocial/user/verifyemailaddress?emailId="+emailId+"&userId="+userId
				+"\n\nIf you have questions or concerns, please contact us at support@intactyou.com"
				+"\n\nCheers\nTeam Intactyou";
		
		emailService.sendEmail(emailId, subject,textBody);
	}
	
	public void welcomeEmail(String emailId,String name)
	{
		String subject="Welcome to Intactyou: Lets begin the ride";
		String textBody="Dear "+name
				+ "\n\nWe really appreciate you for taking a step forward to build your stories by signing up for Intactyou."
				+"\n\nThere is a quote \"The best and most beautiful things in world cannot be seen or touched but are felt in heart\" by Helen Keller.\n"
				+ "\n\nSo start sharing the most beautiful moments and events which should not be forgotten. We help you to build" 
				+"your story in each and every step. Now start living your life in a changed way, and we promise you that this"
				+"change will be beautiful and you will love it."
				+"\n\nWhen you get bored just click here and explore more."
				+"Let us know how you are enjoying and share your experience by sending us a mail at this support@intactyou.com"
				+"Let's the fun begin!"
				+"\n\nCheers\nTeam Intactyou";

		
		emailService.sendEmail(emailId, subject,textBody);
	}
	
	public Long verifyEmailAddress(String userId,String emailId)
	{
		//System.out.println("Inside IF verifyEmailAddress Service");

		 Long status=userDao.verifyEmailAddress(userId, emailId);
		 if(status==1)
		 {
			 userVO=userDao.getUserByEmailId(emailId);
			 welcomeEmail(emailId, userVO.getUserName());
		 }
		 return status;
			 
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	
	public Long forgetPassword(String emailId)
	{
		if(userDao.checkIfEmailIdAlreadyExists(emailId)>=1)
		{
			UserVO userVO=new UserVO();
			userVO.setEmailId(emailId);
			userVO.setPassword(randomString(5));
			userDao.setTempPassword(userVO);
			forgetPasswordEmail(emailId, userVO.getPassword());
			return 1L;
		}
		else 
			return 0L;
	}
	
	public void forgetPasswordEmail(String emailId,String tempPassword)
	{
		String subject="Intact You Temporary Password Email";
		String textBody="Hi "
				+ "\n\nYour temporary password is \n\n\t"+tempPassword
				+"\n\nPlease login in the app with the temporary password and change your password\n"
				+"\n\nIf you have questions or concerns, please contact us at support@intactyou.com"
				+"\n\nCheers\nTeam Intactyou";
		
		emailService.sendEmail(emailId, subject,textBody);
	}
	
	private String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}

	
}
