package teamproject.user.privilege;

import teamproject.meeting.Meeting;
import java.util.jar.Attributes.Name;
import teamproject.college.Course;
import teamproject.college.Module;
import teamproject.user.Group;
import teamproject.user.people.Lecturer;
import teamproject.user.people.Person;

public abstract class Privilege {

	protected final boolean viewSystemSetting;
	protected final boolean admin;

        protected Privilege(boolean viewSystemSetting, boolean admin)
        {
            this.viewSystemSetting = viewSystemSetting;
            this.admin = admin;
        }
	/**
	 * 
	 * @param person
	 * @param value
         * @return True if success false if failed. 
	 */
	public abstract boolean setAdmin(String email);

	/**
	 * 
	 * @param Meeting
         * @return True if success false if failed. 
	 */
	public abstract boolean remove(int Meeting);

	/**
	 * 
	 * @param group
         * @return True if success false if failed. 
	 */
	public abstract boolean remove(Group group);

	/**
	 * 
	 * @param person
         * @return True if success false if failed. 
	 */
	public abstract boolean add(Person person);

	/**
	 * 
	 * @param person
         * @return True if success false if failed. 
	 */
	public abstract boolean remove(Person person);
	/**
	 * 
	 * @param meeting
         * @return True if success false if failed. 
	 */
	public abstract boolean reserveTimeBlock(Meeting meeting);

	/**
	 * 
	 * @param course
         * @return True if success false if failed. 
	 */
	public abstract boolean addCourse(Course course);

	/**
	 * 
	 * @param meeting
         * @return True if success false if failed. 
	 */
	public abstract boolean schdualeHighMeeting(Meeting meeting);

	/**
	 * 
	 * @param course
	 * @param module
         * @return True if success false if failed. 
	 */
	public abstract boolean addModule(Course course, Module module);
	/**
	 * 
	 * @param module
	 * @param lecture
         * @return True if success false if failed. 
	 */
	public abstract boolean addLecture(Module module, Lecturer lecture);

	/**
	 * 
	 * @param url
         * @return True if success false if failed. 
	 */
	public abstract boolean setDataBaseUrl(String url);
	/**
	 * 
	 * @param name
        * @return True if success false if failed. 
	 */
	public abstract boolean setDataBaseName(Name name);
	/**
	 * 
	 * @param password
         * @return True if success false if failed. 
	 */
	public abstract boolean setDataBasePassword(String password);
	/**
	 * 
	 * @param daysInactive
         * @return True if success false if failed. 
	 */
	public abstract int clearInactiveUsers(int daysInactive);

	public boolean isAdmin()
        {
            return admin;
        }
        
	public boolean canViewSystemSetting()
        {
            return viewSystemSetting;
        }
}