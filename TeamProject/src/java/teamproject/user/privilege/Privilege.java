package teamproject.user.privilege;

import teamproject.meeting.Meeting;
import java.util.jar.Attributes.Name;
import teamproject.college.Course;
import teamproject.college.Module;
import teamproject.user.Group;
import teamproject.user.people.Lecturer;
import teamproject.user.people.Person;

public abstract class Privilege {

	private boolean viewSystemSetting;
	private boolean admin;

	/**
	 * 
	 * @param person
	 * @param value
	 */
	public void setAdmin(Person person, boolean value)
	{
		// TODO - implement Privilege.setAdmin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Meeting
	 */
	public boolean remove(int Meeting)
	{
		// TODO - implement Privilege.remove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param group
	 */
	public boolean remove(Group group)
	{
		// TODO - implement Privilege.remove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param person
	 */
	public boolean add(Person person)
	{
		// TODO - implement Privilege.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param person
	 */
	public boolean remove(Person person)
	{
		// TODO - implement Privilege.remove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param meeting
	 */
	public boolean reserveTimeBlock(Meeting meeting)
	{
		// TODO - implement Privilege.reserveTimeBlock
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param course
	 */
	public boolean addCourse(Course course)
	{
		// TODO - implement Privilege.addCourse
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param meeting
	 */
	public boolean schdualeHighMeeting(Meeting meeting)
	{
		// TODO - implement Privilege.schdualeHighMeeting
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param course
	 * @param module
	 */
	public boolean addModule(Course course, Module module)
	{
		// TODO - implement Privilege.addModule
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param module
	 * @param lecture
	 */
	public boolean addLecture(Module module, Lecturer lecture)
	{
		// TODO - implement Privilege.addLecture
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param url
	 */
	public boolean setDataBaseUrl(String url)
	{
		// TODO - implement Privilege.setDataBaseUrl
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public boolean setDataBaseName(Name name)
	{
		// TODO - implement Privilege.setDataBaseName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public boolean setDataBasePassword(String password)
	{
		// TODO - implement Privilege.setDataBasePassword
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param daysInactive
	 */
	public int clearInactiveUsers(int daysInactive)
	{
		// TODO - implement Privilege.clearInactiveUsers
		throw new UnsupportedOperationException();
	}

	public boolean isAdmin()
	{
		return this.admin;
	}

}