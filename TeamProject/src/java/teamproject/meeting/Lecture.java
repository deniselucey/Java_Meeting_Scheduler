package teamproject.meeting;

import teamproject.user.people.Lecturer;
import java.util.ArrayList;
import teamproject.college.Module;

public class Lecture extends Meeting {

	private Module module;
	private ArrayList<Lecturer> lectures;

        
        public Lecture(String title, String description, String location, Recurrence repeat, MeetingType piority, MeetingPrivacy privacy)
        {
            super(title, description, location, repeat, piority, privacy);
        }
	/**
	 * 
	 * @param lecture
	 */
	public boolean add(Lecture lecture)
	{
		// TODO - implement Lecture.add
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lecture
	 */
	public boolean remove(Lecture lecture)
	{
		// TODO - implement Lecture.remove
		throw new UnsupportedOperationException();
	}

	public Module getModule()
	{
		return this.module;
	}

	/**
	 * 
	 * @param module
	 */
	public boolean setModule(Module module)
	{
		// TODO - implement Lecture.setModule
		throw new UnsupportedOperationException();
	}

	public String toSQL()
	{
		// TODO - implement Lecture.toSQL
		throw new UnsupportedOperationException();
	}

}