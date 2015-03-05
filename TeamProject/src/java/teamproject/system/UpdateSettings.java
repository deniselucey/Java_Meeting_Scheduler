/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.sql.SQLException;
import java.util.HashMap;
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
    private String passwordConfirm;
    private String newEmail="";
    private SqlHandler sqlHandler;
    private String encryptedPassword ="";
    private String password1 ="";
    
    public int changePassword(String email)throws NoSuchAlgorithmException, InvalidKeySpecException {
        int changePasswordResult = 0;
        password1 = encrypt();
        
        try{
            
            
            SystemSetting.initSystemSetting();
            
            String query ="UPDATE User "
                    + "SET password ='"+password1+"' "
                    + "WHERE email ='"+email+"';";
            System.out.println("Query; "+ query);
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
            
          System.out.println("Result: " +changePasswordResult);
            
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
                    + "SET email ='"+newEmail+"' "
                    + "WHERE user_id ='"+ userId +"';";
            System.out.println("Query; "+ query);
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
       encryptedPassword = createHash(password1);
       return encryptedPassword;
    }
    
    public boolean passwordCheck(){
        boolean passwordCheck = true;
        if(!password1.equals("") && (passwordConfirm.equals("") || !password1.equals(passwordConfirm))) 
            {
               
                
               passwordCheck = false;
            }
        return passwordCheck;
    }
    
    
    public boolean emailCheck(){
        boolean emailCheck = true;
        if(newEmail.equals("") || newEmail.indexOf("@") == -1) 
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
        newEmail = emailSupplied;
    }
    
    /**
     * 
     * @param passwordOneSupplied 
     */
    public void setPassword1(String passwordOneSupplied)
    {
        password1 = passwordOneSupplied;
            
    }
    
    /**
     * 
     * @param passwordTwoSupplied 
     */    
    public void setPassword2(String passwordTwoSupplied)
    {
        passwordConfirm = passwordTwoSupplied;
    } 
}
