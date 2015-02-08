package teamproject.system;
/**
 * 
 * @author Ciaran
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import teamproject.sql.SqlHandler;



/**
 *
 * @author zolamcdonald
 */
public class Register implements java.io.Serializable{
    
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String studentNumber = "";
    private String password1 = "";
    private String password2 = "";
    private final Hashtable errors = new Hashtable();
    private final SqlHandler sqlHandler = new SqlHandler();
    
    
      /**
         * 
         * @return 
         */
        public boolean registerDetailsWithDb()
        {
            boolean registered = false;
            String query = "INSERT INTO User(firstname, secondname, student_no, "
                           + "email, password"  
                    + "VALUES(firstName,SecondName, studentNumber,"
                    + "email, password1);";
            try{
                sqlHandler.runStatement(query);
                registered = true;
            }catch(SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            return registered;
        }
    
 
    public boolean validateForm() 
    {
        boolean formDetailsCorrect = true;
    
    
        if (firstName.equals("")) 
        {
            errors.put("firstName","Please enter your first name");
            firstName = "";
            formDetailsCorrect = false;
        }
    
    
        if (lastName.equals("")) 
        {
            errors.put("lastName","Please enter your last name");
            lastName = "";
            formDetailsCorrect = false;
        }
    
    
    
        if (email.equals("") || (email.indexOf('@') == -1)) 
        {
            errors.put("email","Please enter a valid email address");
            email = "";
            formDetailsCorrect = false;
        }
    
    
        if (studentNumber.equals("")) 
        {
            errors.put("studentNumber","Please enter your Student Number");
            password1 = "";
            formDetailsCorrect = false;
        }
   
    
    
        if (password1.equals("")) 
        {
            errors.put("password1","Please enter a valid password");
            password1 = "";
            formDetailsCorrect = false;
        }
    
    
        if (!password1.equals("") && (password2.equals("") || 
            !password1.equals(password2))) 
        {
            errors.put("password2","Please confirm your password");
            password2 = "";
            formDetailsCorrect= false;
        }
    
        return formDetailsCorrect;
    }
  
 
  
 
  
    public void setFirstName(String firstNameSupplied)
    {
        firstName = firstNameSupplied;
    }
        
    public void setLastName(String lastNameSupplied)
    {
        lastName = lastNameSupplied;
    }
        
    public void setEmail(String emailSupplied)
    {
        email = emailSupplied;
    }
        
    public void setStudentNumber(String studentNumberSupplied)
    {
        studentNumber = studentNumberSupplied;
    }
        
    public void setPassword1(String passwordOneSupplied)
    {
        password1 = passwordOneSupplied;
            
    }
        
    public void setPassword2(String passwordTwoSupplied)
    {
        password2 = passwordTwoSupplied;
    } 

    public void setErrors(String key, String msg) 
    {
        errors.put(key,msg);
    }

  
    public String getFirstName()
    {
        return firstName;
    }
        
    public String getLastName()
    {
        return lastName;
    }
        
    public String getEmail()
    {
        return email;
    }
        
    public String getPassword1()
    {
        return password1;
    }
    
    public String getPassword2()
    {
        return password2;
    }
        
    public String getStudentNumber()
    {
        return studentNumber;
    }
    
    public String getErrorMessage(String message) 
    {
        String errorMessage =(String)errors.get(message.trim());
        return (errorMessage == null) ? "":errorMessage;
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
	public boolean isUniqueEmailAddress()
	{
            boolean isUnique = false;
            try{
                String query = "SELECT email"
                    + "FROM User"
                    + "WHERE email = '"+email+"';";
                ResultSet result;
                result = sqlHandler.runQuery(query);
                
                //returns false if there are no rows in the ResultSet.
                if (!result.isBeforeFirst()) {
                //sets isUnquie to true because the email address is not in DB
                isUnique = true;
                }
            } catch (SQLException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            return isUnique;
        }
        
        /**
         * 
         * @return 
         */
	public boolean isAllowedEmailAddress()
	{
            boolean emailAddressContains;
            emailAddressContains = email.contains(SystemSetting.getProperty(
                                                Property.AllowedEmailServices));
            return emailAddressContains;
        }

        
        // NEEDS WORK.
        /**
         * 
         * @return 
         */
	public boolean sendValidationEmail()
	{
            boolean confirmationEmailHasBeenSent = false;
            if(registerDetailsWithDb() == true){
                // send email comfirming that a account has been set up
                
                confirmationEmailHasBeenSent = true;
            }
           return confirmationEmailHasBeenSent;
	}
        
        /**
         * 
         * @return 
         */
        public boolean areEmailDetailsOk()
        {
            boolean emailOk = true;
            if((isValidEmailAddress() == false) || (isAllowedEmailAddress() == false))
            {
                emailOk = false;
            }
            
            return emailOk;
        }
        
        /**
         * 
         * @return 
         */
        public boolean checkDetailsAndRegister(){
           boolean details = false;
            if(areEmailDetailsOk() == true && isUniqueEmailAddress() == true 
                    && passwordCheck() == true)
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
        public boolean passwordCheck( )
        {
            boolean answer = true;
            if(!password1.equals(password2)){
                answer = false;
            }
            return answer;
        }
            
        
}