package teamproject.system;
/**
 * 
 * @author Ciaran
 */
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import java.lang.Object;
import java.sql.ResultSet;
import teamproject.sql.SqlHandler;


public class Register implements java.io.Serializable{

	private String firstName;
	private String lastName;
	private String email;
	private String password1;
	private String password2;
        private String studentNumber;
        
        
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
            String query = "";
            boolean isUnique = true;
            
            //ResultSet result = runQuery(query);
            
            String resultReturned ="";
            
            if(resultReturned.equals(email)){
              isUnique = false;
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

	public boolean sendValidationEmail(String email)
	{
            // TODO - implement Register.sendValidationEmail
            throw new UnsupportedOperationException();
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
        
        public boolean passwordCheck( )
        {
            boolean answer = true;
            
            if(!password1.equals(password2)){
                answer = false;
            }
            return answer;
        }
        
       
        
       
}                    
        
        


