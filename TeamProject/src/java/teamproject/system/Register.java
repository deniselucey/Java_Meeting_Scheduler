package teamproject.system;
/**
 * 
 * @author Ciaran
 */
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;

public class Register implements java.io.Serializable{

	private String firstName;
	private String lastName;
	private String email;
	private String password1;
	private String password2;
        private String studentNumber;
        private final SqlHandler sqlHandler = new SqlHandler();
        
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

	public boolean isUniqueEmailAddress()
	{
            boolean isUnique = false;
            try {
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

	public boolean isAllowedEmailAddress()
	{
            boolean emailAddressContains;
            emailAddressContains = email.contains(SystemSetting.getProperty(
                                                Property.AllowedEmailServices));
            return emailAddressContains;
        }

	public boolean sendValidationEmail()
	{
            // TODO - implement Register.sendValidationEmail
            throw new UnsupportedOperationException();
	}
        
        
        public void registerDetailsWithDb()
        {
            
            String query = "INSERT INTO User(firstname, secondname, student_no, "
                           + "email, password"  
                    + "VALUES(firstName,SecondName, studentNumber,"
                    + "email, password1);";
            
            try{
                sqlHandler.runStatement(query);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        public boolean passwordCheck( )
        {
            boolean answer = true;
            if(!password1.equals(password2)){
                answer = false;
            }
            return answer;
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
        
        public String getPassword1(){
            return password1;
        }
        public String getPassword2(){
            return password2;
        }
        
  
 }                    
        
        


