import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;

public class JavaMailUtil {
   
	public static void main(String[] args) {
    	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Please enter the reciever email:");
 
	
    String email = sc.next();
    
    isValid(email);
    			  
  
    
	}
    
    public static void welcomeEmail(String validEmail) {
   

    	 String to = validEmail;
         String from = "planme.noreply@gmail.com";
         String host = "smtp.gmail.com";

         Properties properties = System.getProperties();
         properties.put("mail.smtp.host", host);
         properties.put("mail.smtp.port", "465");
         properties.put("mail.smtp.ssl.enable", "true");
         properties.put("mail.smtp.auth", "true");

         Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication("planme.noreply@gmail.com", "planme1234");
           }
         });

         try {
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           message.setSubject("Assignment Reminder");
           message.setText("This is the email body");

           Transport.send(message);
         } catch (MessagingException mex) {
           mex.printStackTrace();
         }
   
   }
    public static boolean isValid(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      boolean validity = false;
	      boolean continueRun = true;
	      Scanner in = new Scanner(System.in);
	     
	      
	      while(true) {
	    	  
	    	  if(email.matches(regex)) {
		    	  validity = true;
		    	  System.out.println("Email provided is valid!");
		    	  System.out.println("Sending email...");
	    		  break;

		    	  
		      }
	    	  else {
	    		  System.out.println("Invalid email");
	    		  System.out.println("The email address provided: " + email);
	    		  System.out.println("Please enter a valid email address!");	   
	    		  email = in.next();
	    		  break;
	    	  }
	      }
		return validity;
	      
	     
}
}