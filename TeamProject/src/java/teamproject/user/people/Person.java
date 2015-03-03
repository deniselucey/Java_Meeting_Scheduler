package teamproject.user.people;

/**
 * A Person class which provides a general class and methods for all users, 
 * regardless of whether they are staff, lecturers, admin or students.
 * 
 * @author Denise Lucey - 112700291
 */
public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
      
        /**
	 * Retrieves the user id of a person. 
         * 
         * @return id - user id of the person.
	 */
        public int getId()
	{
            return id;
	}
        
        /**
	 * Retrieves the first name of a person. 
         * 
         * @return firstName - first name of the person.
	 */
        public String getFirstName()
	{
            return firstName;
	}
        
        /**
	 * Retrieves the last name of a person. 
         * 
         * @return lastName - last name of the person.
	 */
        public String getLastName()
	{
            return lastName;
	}

        /**
	 * Retrieves the email address of a person. 
         * 
         * @return email - email of the person.
	 */
        public String getEmail()
	{
            return email;
	}
            
        /**
	 * Sets the person's user id.
         * 
         * @param id - user id of the person.
	 */
         public void setId(int id)
	{
            this.id = id;
	}
        
        /**
	 * Sets the person's first name.
         * 
         * @param firstName - first name of the person.
	 */
        public void setFirstName(String firstName)
	{
            this.firstName = firstName;
	}
        
        /**
	 * Sets the person's last name.
         * 
         * @param lastName - last name of the person.
	 */
        public void setLastName(String lastName)
	{
            this.lastName = lastName;
	}
        
        /**
	 * Sets the person's email address.
         * 
         * @param email - email address of the person.
	 */
        public void setEmail(String email)
	{
            this.email = email;
	}       
        
	public boolean scheduleMeeting()
	{
		// TODO - implement Person.scheduleMeeting
		throw new UnsupportedOperationException();
	}

	public boolean confirmMeeting()
	{
		// TODO - implement Person.confirmMeeting
		throw new UnsupportedOperationException();
	}

	public boolean sendNotify()
	{
		// TODO - implement Person.notify
		throw new UnsupportedOperationException();
	}

}