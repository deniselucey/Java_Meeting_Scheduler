package teamproject.system;
/**
 * 
 * @author Ciaran
 */

public class Register implements java.io.Serializable{

	private String firstName = "Ciaran";
	private String lastName;
	private String email;
	private String password1;
	private String password2;
        

	public boolean isValidEmailAddress()
	{
		// TODO - implement Register.isValidEmailAddress
		throw new UnsupportedOperationException();
	}

	public boolean isUniqueEmailAddress()
	{
		// TODO - implement Register.isUniqueEmailAddress
		throw new UnsupportedOperationException();
	}

	public boolean isAllowedEmailAddress()
	{
		// TODO - implement Register.isAllowedEmailAddress
		throw new UnsupportedOperationException();
	}

	public boolean sendValidationEmail()
	{
		// TODO - implement Register.sendValidationEmail
		throw new UnsupportedOperationException();
	}
    
        public void setFirstName(String firstNameSupplied){
            firstName = firstNameSupplied;
            
        }
        
        public void setLastName(String lastNameSupplied)
        {
            lastName = lastNameSupplied;
        }
        
        public void email(String emailSupplied)
        {
            email = emailSupplied;
        }
        
        public void setPassword(String passwordOneSupplied, String passwordTwoSupplied)
        {
            password1 = passwordOneSupplied;
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
        
       
}
        
        


