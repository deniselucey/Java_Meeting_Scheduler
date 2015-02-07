package teamproject.system;
/**
 * 
 * @author Ciaran
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;


public class Register implements java.io.Serializable{

	private String firstName;
	private String lastName;
	private String email;
	private String password1;
	private String password2;
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost8080";
        static final String USER = "username";
        static final String PASSWORD = "password";
        
        

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
                // TODO - implement Register.isUniqueEmailAddress
		throw new UnsupportedOperationException();
	}

	public boolean isAllowedEmailAddress()
	{
            // TODO - implement Register.isAllowedEmailAddress
            throw new UnsupportedOperationException();
	}

	public boolean sendValidationEmail(String email)
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
        
        public void setEmail(String emailSupplied)
        {
            email = emailSupplied;
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
        
       
        
        //Not finished
        public void submitDetailsToDb(){
             Connection connection;
             Statement statement;
            
             try{
               
               // Register the jdbc driver
               Class.forName("com.mysql.jdbc.Driver");
               // Open connection
               connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
               //Excute query
               statement = connection.createStatement();
               //Query to be excuted 
               String sqlQuery = "";
               statement.executeUpdate(sqlQuery);
               //close connection
               statement.close();
            }catch(SQLException | ClassNotFoundException err){
                System.out.println(err);
            }
        }
}
        
        


