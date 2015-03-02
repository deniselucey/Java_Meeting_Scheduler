/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
/**
 *
 * @author zolamcdonald
 */
public class Email {
    
    
    private String sendingEmail = "cs3305groupproject@gmail.com";
    private String sendingEmailPassword ="uccgroup9";
    private String email;
    private String emailBody;
    private String emailSubject;
    private String host = "smtp.gmail.com";
    private String port = "mail.smtp.port";
    
    private boolean emailResult;

    public boolean sendEmail(String emailSubject1,String emailText, String email1){
        email = email1;
        emailSubject = emailSubject1;
        emailBody = emailText;
        // Gets the system properties object
        Properties properties = new Properties();

        // Setups mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        
        

        // Gets the default Session object.
        Session emailSession = Session.getInstance(properties, 
                new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(sendingEmail,sendingEmailPassword);
            }
        });

        try{

            // Creates a default MimeMessage object.
            MimeMessage message = new MimeMessage(emailSession);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sendingEmail));
            
            
            // Set To: header field of the header.
            if(email.indexOf(",")>0){
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                
            }else{
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));  
            }
            
            // Sets the email Subject: header field
            message.setSubject( emailSubject);
            // Sets the body of the email.
            message.setText(emailBody);
            // Sends the email.
            Transport.send(message);
            //Sets the emailResult to confirm the email has been sent.
            emailResult = true;
        }catch (MessagingException error) {
                
                emailResult = false; 
                System.out.println("Error "+error);
        }
        return emailResult;
    }               

   
}
