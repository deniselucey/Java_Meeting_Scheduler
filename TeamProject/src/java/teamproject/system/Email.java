/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;

import static java.lang.Math.random;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
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
    private SqlHandler sqlHandler;
    private String randomNumber;

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

    
    public boolean updateDb(String email1){
        
         email = email1;
        
        /**
         * Sets is isRegistered to false.
         */
        boolean isRegistered = false;
        try{
            SecureRandom random = new SecureRandom();
            randomNumber = new BigInteger(48, random).toString(16);
            SystemSetting.initSystemSetting();
            /**
             *  Stores the statement as a string.
             */
            String query1 ="INSERT INTO Register "+
                            "VALUE( (SELECT user_id FROM `User` WHERE email = '"+ email + "'),"
                           + "0, NOW(),'"+randomNumber +"')" ;
            
            
            /**
             * Creates a new sqlHandler.
             */
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            if(sqlHandler.runStatement(query1) == 1){
                isRegistered =true;
            }
           
        }catch(SQLException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Returns true if the user's details have been registered in the db,
         * Returns false if the user's details haven't been registered.
         */
        return isRegistered;
    }
    
 
    
    
    public boolean confirmAccount(String pin1, int userId1){
        int confirmationResult;
        boolean accountConfirmed = false;
        String pin = pin1;
        String query1;
        String query2;
        int userId = userId1;
        query1 ="SELECT pin, user_id FROM Register WHERE pin ='"+pin+"'AND user_id ='"+userId+"';";
        try{
           SystemSetting.initSystemSetting();
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            ResultSet queryResult = sqlHandler.runQuery(query1);
            
                if(queryResult.next()){
                    
                    String pinResult = queryResult.getString("pin");
                    int userIdResult = queryResult.getInt("user_id");
                        query2 ="DELETE FROM Register "
                                + "WHERE pin ='"+pinResult+"' AND user_id ="+userIdResult+";";
                        confirmationResult = sqlHandler.runStatement(query2);
                        
                        if(confirmationResult == 1){
                            accountConfirmed = true;
                            return accountConfirmed;
                        }
                    
                    
                }
         }catch(SQLException ex){
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accountConfirmed;
    }
    
    
    


    public String getRandomNumber(){
            return randomNumber;
    }
}


