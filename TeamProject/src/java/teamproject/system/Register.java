package teamproject.system;
/**
 * 
 * @author Ciaran McDonald
 */

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import teamproject.sql.SqlHandler;
import static teamproject.system.PasswordHash.createHash;


public class Register implements java.io.Serializable{
    
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private int studentNumber;
    private String password1 = "";
    private String password2 = "";
    private final HashMap errors = new HashMap();
    private SqlHandler sqlHandler;
    private String emailUnique ="";
    private String encryptedPassword ="";
    
  /**
     * 
     * @return 
     * @throws java.security.NoSuchAlgorithmException 
     * @throws java.security.spec.InvalidKeySpecException 
     * 
     */
    public boolean registerDetailsWithDb() throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
        String password = encrypt();
        boolean registered1 = false;
        boolean registered2 = false;
        boolean isRegistered = false;
        try{
            SystemSetting.initSystemSetting();
            String query1 = "INSERT INTO User(firstname, secondname,"
                           + "email, password)"  
                    + "VALUES('" + firstName+ "','" + lastName +"','" +
                     email +"','" + password +"');" ;
            
            String query2 = "INSERT INTO student(student_number)" +
                             "VALUE('" +studentNumber+"');";
       
            sqlHandler = new SqlHandler();
            
            registered1 = sqlHandler.runStatement(query1);
            registered2 = sqlHandler.runStatement(query2);
            if(registered1 && registered2 == true){
                isRegistered = true;
            }
           
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isRegistered;
    }
    
    
    private String encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException{
       encryptedPassword = createHash(password1);
       return encryptedPassword;
    }
    
    
    /**
     * 
     * @return 
     *  
     */
    public boolean isUniqueEmailAddress() 
    {
        
        boolean isUnique = false;
        try{
            
            sqlHandler = new SqlHandler();
            String query = "SELECT email "
                    + "FROM User "
                    + "WHERE email ='"+email+"';";
            
            ResultSet queryResult;
            queryResult = sqlHandler.runQuery(query);
         
            //returns false if there are no rows in the ResultSet.
            if (!queryResult.isBeforeFirst() && !queryResult.next() ) 
            {
                //sets isUnquie to true because the email address is not in DB
                isUnique = true;
            }
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUnique;
    }
    
    
    // NEEDS WORK.
    /**
     * 
     * @return 
     * @throws java.security.NoSuchAlgorithmException 
     * @throws java.security.spec.InvalidKeySpecException 
     */
    public boolean sendValidationEmail() throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
        boolean confirmationEmailHasBeenSent = false;
        if(registerDetailsWithDb()){
            // send email comfirming that a account has been set up
                
            confirmationEmailHasBeenSent = true;
        }
        return confirmationEmailHasBeenSent;
    }
    
    
    /**
     * 
     * @return 
     */
    public boolean isAllowedEmailAddress()
    {
        boolean emailAddressContains;
        emailAddressContains = email.contains(SystemSetting.getProperty(
                                                Property.AllowedEmailServices,null));
        return emailAddressContains;
    }
    
    
    /**
     * 
     * @return 
     * @throws java.security.NoSuchAlgorithmException 
     * @throws java.security.spec.InvalidKeySpecException 
     */
    public boolean checkDetailsAndRegister() throws NoSuchAlgorithmException, InvalidKeySpecException{
        boolean details = false;
        if( isUniqueEmailAddress())
        {
            details = true;
            registerDetailsWithDb();
        }
        //if returns true details are ok and are being registered with db
        // if returns false details are not correct and arent being registered.
        return details;   
    }
    
    
     /**
     * 
     * @return 
     */
        public boolean isValidEmailAddress()
	{
            boolean isEmailVaild = false;
            try{
                InternetAddress internetAddress = new InternetAddress(email);
                internetAddress.validate();
                isEmailVaild = true;
            }catch(AddressException e ){
                System.out.println("Error" );
            }
            return isEmailVaild;
        }
    
   /**
    * 
    * @return 
    */
    public boolean validateForm() 
    {
        boolean formDetailsCorrect = true;
    
        
        if(firstName.equals("")) 
        {
            errors.put("firstName","Please enter your first name");
            firstName = "";
            formDetailsCorrect = false;
        }
    
    
        if(lastName.equals("")) 
        {
            errors.put("lastName","Please enter your last name");
            lastName = "";
            formDetailsCorrect = false;
        }
    
 
        if(email.equals("") || (email.indexOf('@') == -1)) 
        {
            errors.put("email","Please enter a valid email address");
            email = "";
            formDetailsCorrect = false;
        }
    
        if(password1.equals("")) 
        {
            errors.put("password1","Please enter a valid password");
            password1 = "";
            formDetailsCorrect = false;
        }
    
    
        if(!password1.equals("") && (password2.equals("") || 
            !password1.equals(password2))) 
        {
            errors.put("password2","Please confirm your password");
            password2 = "";
            formDetailsCorrect= false;
        }
        
        if(isUniqueEmailAddress() == false)
        {
            errors.put("emailUnique","Email address has already been registered");
            email = "";
            formDetailsCorrect= false;
        }
    
        return formDetailsCorrect;
    }
  
 
  
 
    /**
     * 
     * @param firstNameSupplied 
     */
    public void setFirstName(String firstNameSupplied)
    {
        firstName = firstNameSupplied;
    }
    
    
    /**
     * 
     * @param lastNameSupplied 
     */
    public void setLastName(String lastNameSupplied)
    {
        lastName = lastNameSupplied;
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
     * @param studentNumberSupplied 
     */
    public void setStudentNumber(int studentNumberSupplied)
    {
        studentNumber = studentNumberSupplied;
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
        password2 = passwordTwoSupplied;
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
     * @return 
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * 
     * @return 
     */
    public String getLastName()
    {
        return lastName;
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
    public String getPassword1()
    {
        return password1;
    }
    
    /**
     * 
     * @return 
     */
    public String getPassword2()
    {
        return password2;
    }
    /**
     * 
     * @return 
     */   
    public int getStudentNumber()
    {
        return studentNumber;
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
 }