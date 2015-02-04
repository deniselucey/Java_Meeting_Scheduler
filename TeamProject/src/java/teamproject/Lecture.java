package teamproject;

import teamproject.user.people.Lecturer;
import java.util.ArrayList;
import teamproject.college.Module;
import teamproject.user.*;

public class Lecture extends Meeting {

	private Module module;
	private ArrayList<Lecturer> lectures;

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