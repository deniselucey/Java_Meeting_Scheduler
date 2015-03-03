
package teamproject.system;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import java.sql.ResultSet;
import static teamproject.system.PasswordHash.validatePassword;

/**
 *
 * @author zolamcdonald
 */
public class Login {
     private String email = "";
     private String password = "";
     private int userId;
     private boolean admin;
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
    
    public boolean checkDb() throws NoSuchAlgorithmException, InvalidKeySpecException{
        boolean inDb = false;
        
        try{
            SystemSetting.initSystemSetting();
            String query = "SELECT email,password,user_id,admin "+
                           "FROM User "+
                           "WHERE email ='"+email+"' AND user_id NOT IN (SELECT user_id FROM Register);";
            
            ResultSet queryResult;
            sqlHandler = new SqlHandler();
            queryResult = sqlHandler.runQuery(query);
            
            if(queryResult.isBeforeFirst())
            {
                queryResult.next();
                String emailResult = queryResult.getString("email");
                String passwordResult = queryResult.getString("password");
               
                
               
                if(emailResult.equals(email) && validatePassword(password, passwordResult))
                {
                    inDb = true;
                    userId = queryResult.getInt("user_id");
                    admin = queryResult.getBoolean("admin");
                }
            }else{
                return inDb;
            }
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inDb;
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
    
   public int getUserId(){
       return userId;
   }
   
   public boolean getIsAdmin(){
       return admin;
   }
    
   
    
    
}


