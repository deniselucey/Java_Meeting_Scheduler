package teamproject.system;
/**
 * 
 * @author zolamcdonald
 */

import static java.lang.Math.random;
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
    private int studentNumber = 0;
    private String password1 = "";
    private String password2 = "";
    private final HashMap errors = new HashMap();
    private SqlHandler sqlHandler;
    private String emailUnique ="";
    private String encryptedPassword ="";
    private String password =""; 

  
    
    /**
     * Registers the new users details in the db on sign up
     * Returns true if the happens successfully,
     * Returns false if the details are registered.
     * @return a boolean.
     * @throws java.security.NoSuchAlgorithmException 
     * @throws java.security.spec.InvalidKeySpecException 
     * 
     */
     public boolean registerDetailsWithDb() throws NoSuchAlgorithmException, InvalidKeySpecException 
    {
         
       /**
        * Encrypts the password.
        */
        password = encrypt();
        int registered1;
        int registered2;
        /**
         * Sets is isRegistered to false.
         */
        boolean isRegistered = false;
        try{
            
            SystemSetting.initSystemSetting();
            /**
             *  Stores the first statement as a string.
             */
            String query1 = "INSERT INTO User(firstname, secondname, "
                           + "email, password)"  
                    + "VALUES('" + firstName+ "','" + lastName +"','" +
                     email +"','" + password +"');"; 
           
            /**
             * Stores the second statement as a string.
             */
            String query2 = "INSERT INTO student(student_id,student_number)" +
                             "VALUE( (SELECT user_id FROM `User` WHERE email = '"+ email + "'),  "
                    + "'"+studentNumber+"');";
            
            
   
            /**
             * Creates a new sqlHandler.
             */
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            registered1 = sqlHandler.runStatement(query1);
            registered2 = sqlHandler.runStatement(query2);
            /**
             * If both statements have being successfully run,
             * It sets isRegistered to true.
             */
            if(registered1 == 1 && registered2 == 1){
                
                isRegistered = true;
            }
           
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Returns true if the user's details have been registered in the db,
         * Returns false if the user's details haven't been registered.
         */
        return isRegistered;
    }
    
     /**
     * Encrypts the password before it is added to the db.
     * @return String.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException 
     */
    private String encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException{
       encryptedPassword = createHash(password1);
       return encryptedPassword;
    }
    
    
    /**
     * Checks is the email the user is providing is unique and
     * it hasn't been registered already, It returns 
     * True if the the email is unique,
     * False if the email has been already registered.
     * @return a boolean
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
        boolean emailAddressContains = false;
        try{
           
            emailAddressContains = email.contains(SystemSetting.getProperty(
                                                Property.AllowedEmailServices,null));
        }catch(Exception e){
        System.out.println("error");
        }
        return emailAddressContains;
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
    
 
        if(email.equals("") || email.indexOf("@") == -1)  //(!isValidEmailAddress()) ||!isAllowedEmailAddress() ) 
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
    
//    public boolean checkIsActivation(){
//        boolean isActivated = false;
//        int userIdResult= 0;
//        String query ="SELECT confirmation "
//                + "FROM Register "
//                + "WHERE email ='"+email;";
//        try{
//            SystemSetting.initSystemSetting();
//            sqlHandler = new SqlHandler();
//            /**
//             * Runs the SQL statement's using the sqlHandler
//             * Stores if the statement was successful or not in variable as
//             * a 1 or 0.
//             */
//            ResultSet queryResult = sqlHandler.runQuery(query);
//            
//            if(queryResult.next()){
//                //userIdResult = queryResult.getInt("user_id");
//                isActivated = true;
//                return isActivated;
//            }
//        }catch(SQLException ex){
//            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return isActivated;
//    }
//  
 
  
 
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
    
    public int getUserId(){
        int userIdResult= 0;
        String query ="SELECT user_id "
                + "FROM User "
                + "WHERE email ='"+email+"';";
        try{
            SystemSetting.initSystemSetting();
            sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            ResultSet queryResult = sqlHandler.runQuery(query);
            
            if(queryResult.next()){
                userIdResult = queryResult.getInt("user_id");
                return userIdResult;
            }
        }catch(SQLException ex){
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userIdResult;
    }
 }