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
    
    public int changePassword(String email)throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("new password "+ password);
        
        int changePasswordResult = 0;
        password1 = encrypt();
        System.out.println("Encrypted new password: "+ password1);
        
        try{
            
            
            SystemSetting.initSystemSetting();
            
            String query ="UPDATE User "
                    + "SET password ='"+password1+"' "
                    + "WHERE email ='"+email+"';";
           
            /**
             * Creates a new sqlHandler.
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
    
    
    
    
    public int changeEmail(int userId){
        int  changeEmailResult = 0;
        try{
            SystemSetting.initSystemSetting();
            
            String query ="UPDATE User "
                    + "SET email ='"+email+"' "
                    + "WHERE user_id ='"+ userId +"';";
            
            /**
             * Creates a new sqlHandler.
             */
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            changeEmailResult = sqlHandler.runStatement(query);
            
          System.out.println("Result: " +changeEmailResult);
            
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
    
    public boolean passwordCheck(){
        boolean passwordCheck = true;
        if(!password.equals("") && (passwordConfirm.equals("") || !password.equals(passwordConfirm))) 
            {
              passwordCheck = false;
            }
        return passwordCheck;
    }
    
    /**
     *
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
     * 
     * @param emailSupplied 
     */
    public void setEmail(String emailSupplied)
    {
        email = emailSupplied;
    }
    
    /**
     * 
     * @param passwordOneSupplied 
     */
    public void setPassword(String passwordOneSupplied)
    {
        password = passwordOneSupplied;
            
    }
    
    /**
     * 
     * @param passwordTwoSupplied 
     */    
    public void setPasswordConfirm(String passwordTwoSupplied)
    {
        passwordConfirm = passwordTwoSupplied;
    } 
}
