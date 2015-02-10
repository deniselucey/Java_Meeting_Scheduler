/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.system;

import java.util.HashMap;

/**
 *
 * @author zolamcdonald
 */
public class Login {
     private String email = "";
     private String password = "";
     private final HashMap errors = new HashMap();
    /**
    * 
    * @return 
    */
    public boolean validateForm() 
    {
        boolean formDetailsCorrect = true;
    
        
 
        if (email.equals("") || (email.indexOf('@') == -1)) 
        {
            errors.put("email","Please enter a valid email address");
            email = "";
            formDetailsCorrect = false;
        }
    
        if (password.equals("")) 
        {
            errors.put("password1","Please enter a valid password");
            password = "";
            formDetailsCorrect = false;
        }
    
    
      
    
        return formDetailsCorrect;
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


