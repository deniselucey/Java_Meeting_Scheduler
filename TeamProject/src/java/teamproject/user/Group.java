package teamproject.user;

import teamproject.user.people.Person;
import java.util.ArrayList;

public class Group {

	private ArrayList<Person> member;
	private Privacy privacy;
	private ArrayList<Person> founders;
	private ArrayList<Person> group;
	private String HopePage;
        private int id;
	/**
	 * 
	 * @param Url
	 */
	public boolean setHomePage(String Url)
	{
		// TODO - implement Group.setHomePage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newPrivacy
	 */
	public boolean setPrivacy(Privacy newPrivacy)
	{
		// TODO - implement Group.setPrivacy
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param person
	 */
	public boolean addFounder(Person person)
	{
		// TODO - implement Group.addFounder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param person
	 */
	public boolean removeFounder(Person person)
	{
		// TODO - implement Group.removeFounder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 */
	public boolean deleteGroup(User user)
	{
		// TODO - implement Group.deleteGroup
		throw new UnsupportedOperationException();
	}

	public boolean saveChanges()
	{
		// TODO - implement Group.saveChanges
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param message
	 * @param people
	 */
	public int notify(String message, ArrayList<Person> people)
	{
		// TODO - implement Group.notify
		throw new UnsupportedOperationException();
	}
        
        public int getId()
        {
            return id;
        }
}