/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import static teamproject.system.PasswordHash.createHash;

/**
 *
 * @author zolamcdonald
 */
public class UpdateSettings {
    private String password ="";
    private String passwordConfirm ="";
    private String email="";
    private SqlHandler sqlHandler;
    private String encryptedPassword ="";
    private String password1 ="";
    
    /**
     * Changes the password of the user
     * @param email
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException 
     */
    public int changePassword(String email)throws NoSuchAlgorithmException, InvalidKeySpecException {
        int changePasswordResult = 0;
        /**
         * encrypts the password.
         */
        password1 = encrypt();
        try{
            /**
             * initializes system settings and loads saved settings.
             */
            SystemSetting.initSystemSetting();
            /**
             * Stores the statement as a string.
             */
            String query ="UPDATE User "
                    + "SET password ='"+password1+"' "
                    + "WHERE email ='"+email+"';";
            /**
             * initializes the SQL Handler.
             */
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            changePasswordResult = sqlHandler.runStatement(query);
       
        }catch(SQLException ex) {
            Logger.getLogger(UpdateSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return changePasswordResult;
    }
    
    
    
    /**
     * Changes the email of the user.
     * @param userId
     * @return 
     */
    public int changeEmail(int userId){
        int  changeEmailResult = 0;
        try{
            /**
             * initializes system settings and loads saved settings.
             */
            SystemSetting.initSystemSetting();
            /**
             * Stores the statement as a string.
             */
            String query ="UPDATE User "
                    + "SET email ='"+email+"' "
                    + "WHERE user_id ='"+ userId +"';";
            
            /**
             * initializes the SQL Handler.
             */
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            changeEmailResult = sqlHandler.runStatement(query);
        }catch(SQLException ex) {
            Logger.getLogger(UpdateSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return changeEmailResult;
    }
    
    
    /**
     * Encrypts the password before it is added to the db.
     * @return String.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException 
     */
    private String encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException{
       encryptedPassword = createHash(password);
       return encryptedPassword;
    }
    
    /**
     * Checks if the password meets requirements.
     * @return 
     */
    public boolean passwordCheck(){
        boolean passwordCheck = true;
        if(!password.equals("") && (passwordConfirm.equals("") || !password.equals(passwordConfirm))) 
            {
              passwordCheck = false;
            }
        return passwordCheck;
    }
    
    /**
     * Checks if the email meets requirements.
     * @param email
     * @return
     */
    public boolean emailCheck(String email){
        boolean emailCheck = true;
        if(email.equals("") || email.indexOf("@") == -1) 
        {
           emailCheck = false;
        }
        return emailCheck;
    }
    
    
    /**
     * Sets the email passed in from the form to the email var in the java class
     * @param emailSupplied 
     */
    public void setEmail(String emailSupplied)
    {
        email = emailSupplied;
    }
    
    /**
     * Sets the first password passed in from the form to the first password var in the java class.
     * @param passwordOneSupplied 
     */
    public void setPassword(String passwordOneSupplied)
    {
        password = passwordOneSupplied;
            
    }
    
    /**
     * Sets the first password passed in from the form to the first password var in the java class.
     * @param passwordTwoSupplied 
     */    
    public void setPasswordConfirm(String passwordTwoSupplied)
    {
        passwordConfirm = passwordTwoSupplied;
    } 
}
