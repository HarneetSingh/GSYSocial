package com.motionapps.GSYSocial.services;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.motionapps.GSYSocial.util.Constants;

public class EmailService {
	
	
	private static final String FROM = "IntactYou <support@intactyou.com>";  // Replace with your "From" address. This address must be verified.
//	 static final String TO = "harneetsingh17@gmail.com"; // Replace with a "To" address. If you have not yet requested
//	                                                      // production access, this address must be verified.
//	    static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
//	    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
	 private static Regions AWS_REGION = Regions.US_WEST_2;  
	    
	    public Long sendEmail(String to, String subjectText, String bodyText)
	    {
	    	// Construct an object to contain the recipient address.
	        Destination destination = new Destination().withToAddresses(new String[]{to});

	        // Create the subject and body of the message.
	        Content subject = new Content().withData(subjectText);
	        Content textBody = new Content().withData(bodyText);
	        Body body = new Body().withText(textBody);

	        // Create a message with the specified subject and body.
	        Message message = new Message().withSubject(subject).withBody(body);

	        // Assemble the email.
	        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

	        try {
	            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

	            /*
	             * The ProfileCredentialsProvider will return your [default]
	             * credential profile by reading from the credentials file located at
	             * (~/.aws/credentials).
	             *
	             * TransferManager manages a pool of threads, so we create a
	             * single instance and share it throughout our application.
	             */
	            AWSCredentials credentials = null;
	            try {
	                //credentials = new ProfileCredentialsProvider().getCredentials();
	                credentials=new BasicAWSCredentials(Constants.aws_access_key_id, Constants.aws_secret_access_key);
	            } catch (Exception e) {
	                throw new AmazonClientException(
	                        "Cannot load the credentials from the credential profiles file. " +
	                        "Please make sure that your credentials file is at the correct " +
	                        "location (~/.aws/credentials), and is in valid format.",
	                        e);
	            }

	            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
	            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

	            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
	            // access status, sending limits, and Amazon SES identity-related settings are specific to a given
	            // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
	            // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
	            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
	            Region REGION = Region.getRegion(Regions.US_WEST_2);
	            client.setRegion(REGION);

	            // Send the email.
	            client.sendEmail(request);
	            System.out.println("Email sent!");
	            return 1L;

	        } catch (Exception ex) {
	            System.out.println("The email was not sent.");
	            System.out.println("Error message: " + ex.getMessage());
	            return 0L;
	        }
	    }
	    
	    public Long sendRawEmail(String to, String subjectText, String bodyText)
	    {
	    	 Session session = Session.getDefaultInstance(new Properties());
	         MimeMessage message = new MimeMessage(session);
	         try {
				message.setSubject(subjectText, "UTF-8");
				message.setFrom(new InternetAddress(FROM));
				message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return 1L;
	    }
}

