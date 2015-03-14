
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
     private int lectureId ;
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
             /**
             * initializes system settings and loads saved settings.
             */
            SystemSetting.initSystemSetting();
             /**
             *  Stores the query as a string.
             */
            String query1 = "SELECT email,password,user_id,admin,lecture_id "+
                           "FROM User LEFT JOIN lecturer " +
                           "ON User.user_id = lecturer.lecture_id "+
                           "WHERE email ='"+email+"' AND user_id NOT IN (SELECT user_id FROM Register);";
            
           
            ResultSet queryResult;
            /**
             *  initializes the SQL Handler.
             */
            sqlHandler = new SqlHandler();
            /**
             * runs the query.
             */
            queryResult = sqlHandler.runQuery(query1);
            
            if(queryResult.isBeforeFirst())
            {
                queryResult.next();
                /**
                 * Gets the email from the query result.
                 */
                String emailResult = queryResult.getString("email");
                /**
                 * Gets the password from the query.
                 */
                String passwordResult = queryResult.getString("password");
               
                
               /**
                * Checks is the email passed in by the form is the same the email in the database,
                * Checks if the password passed in by the form is the same as the password in the database. 
                */
                if(emailResult.equals(email) && validatePassword(password, passwordResult))
                {
                    /**
                     * sets inDb to true and then gets various details from query.
                     */
                    inDb = true;
                    userId = queryResult.getInt("user_id");
                    admin = queryResult.getBoolean("admin");
                    lectureId = queryResult.getInt("lecture_id");
                    
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
   
   public int getIsLecturer(){
       return lectureId;
   }
    
   
    
    
}


