/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import java.sql.ResultSet;


/**
 *
 * @author zolamcdonald
 */
public class Login {
     private String email = "";
     private String password = "";
     private SqlHandler sqlHandler;
     private final HashMap errors = new HashMap();
    /**
    * 
    * @return 
    */
    public boolean validateForm() 
    {
        boolean formDetailsCorrect = true;
    
        if(email.equals("") || (email.indexOf('@') == -1)) 
        {
            errors.put("email","Please enter a valid email address");
            email = "";
            formDetailsCorrect = false;
        }
    
        if(password.equals("")) 
        {
            errors.put("password1","Please enter a valid password");
            password = "";
            formDetailsCorrect = false;
        }
        return formDetailsCorrect;
    }
    
    public boolean checkDb(){
        boolean inDb = false;
        
        try{
            SystemSetting.initSystemSetting();
            String query = "SELECT email,password "+
                           "FROM User "+
                           "WHERE email ='"+email+"' && password ='"+password+"';";
            
            ResultSet queryResult;
            sqlHandler = new SqlHandler();
            queryResult = sqlHandler.runQuery(query);
            
            if(queryResult.isBeforeFirst() == true)
            {
                queryResult.next();
                String emailResult = queryResult.getString("email");
                String passwordResult = queryResult.getString("password");
               
                if(emailResult.equals(email) && passwordResult.equals(password)){
                 
                    inDb = true;
                }
            }else{
                
                return inDb;
            }
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inDb;
    }
    
            
    private void encrypt(){
        
       
        
    }
    
    private String decrypt(){
     String password;
         password = "";
     
     
     return password;
    }
 
    
    /**
     * 
     * @param key
     * @param msg 
     */
    public void setErrors(String key, String msg) 
    {
        errors.put(key,msg);
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
     * @param message
     * @return 
     */
    public String getErrorMessage(String message) 
    {
        String errorMessage =(String)errors.get(message.trim());
        return (errorMessage == null) ? "":errorMessage;
    }
    
    /**
     * 
     * @return 
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * 
     * @return 
     */
    public String getPassword()
    {
        return password;
    }
    
   
    
    
}


