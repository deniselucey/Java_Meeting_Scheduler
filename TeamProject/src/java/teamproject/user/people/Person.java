package teamproject.user.people;

public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
      
        
        public int getId()
	{
            return id;
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
            
         public void setId(int id)
	{
            this.id = id;
	}
        
        public void setFirstName(String firstName)
	{
            this.firstName = firstName;
	}
        
        public void setLastName(String lastName)
	{
            this.lastName = lastName;
	}

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