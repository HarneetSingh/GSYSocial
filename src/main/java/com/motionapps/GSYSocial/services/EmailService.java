package com.motionapps.GSYSocial.services;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.motionapps.GSYSocial.util.Constants;


public class EmailService {


	private static final String FROM = "IntactYou <support@intactyou.com>"; 
	private static Regions AWS_REGION = Regions.US_WEST_2;  
    @Autowired
	private ServletContext servletContext;


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
			
			 // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<img src=\"cid:image\">"+bodyText;
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
//	         InputStream csv = 
//	        		   this.getClass().getResourceAsStream("mail-header.jpg");
//	         URL imageUrl = this.getClass().getResource("/images/mail-header.jpg");
	         String path=servletContext.getRealPath("").toString();
	         System.out.println(path);
	         
	         DataSource fds = new FileDataSource(path+Constants.imageLocation);
//	         ServletContext context = getContext();
//	         String fullPath = applicationContext.getRealPath("/images/mail-header.jpg");
	         
//	         DataSource fds = new FileDataSource(new File(imageUrl.getFile()));
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);
//		    // Alternative TEXT/HTML content
//	        MimeMultipart cover = new MimeMultipart("alternative");
//	        MimeBodyPart html = new MimeBodyPart();
//	        cover.addBodyPart(html);
//	        
//	        
//			// message contains HTML markups
//			String abc = "<i>Greetings!</i><br>";
//			abc += "<b>Wish you a nice day!</b><br>";
//			abc += "<font color=red>Duke</font>";
//			html.setContent(abc,"text/html");
//			message.setContent(cover);

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
			Region REGION = Region.getRegion(AWS_REGION);
			client.setRegion(REGION);

			// Print the raw email content on the console
//			PrintStream out = System.out;
//			message.writeTo(out);

			// Send the email.
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
			client.sendRawEmail(rawEmailRequest);
			System.out.println("Email sent!");
			return 1L;

		} catch (Exception ex) {
			System.out.println("Email Failed");
			System.err.println("Error message: " + ex.getMessage());
			ex.printStackTrace();
			return 0L;

		}

}




}

