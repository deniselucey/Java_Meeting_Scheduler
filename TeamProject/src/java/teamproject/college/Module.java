package teamproject.college;

import teamproject.meeting.Lecture;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Module {

	private Course course;
	private ArrayList<Lecture> lectures;
	private ArrayList<Lecture> lecturers;
	private LocalDateTime startData;
	private LocalDateTime endDate;
	private ArrayList<Semester> semesters;
	private int[][] years;

	public Course getCourse()
	{
		return this.course;
	}

	/**
	 * 
	 * @param course
	 */
	public boolean setCourse(Course course)
	{
		// TODO - implement Module.setCourse
		throw new UnsupportedOperationException();
	}

	public ArrayList<Lecture> getLectures()
	{
		return this.lectures;
	}

	/**
	 * 
	 * @param lectures
	 */
	public boolean setLectures(ArrayList<Lecture> lectures)
	{
		// TODO - implement Module.setLectures
		throw new UnsupportedOperationException();
	}

	public ArrayList<Lecture> getLecturers()
	{
		return this.lecturers;
	}

	/**
	 * 
	 * @param lecturers
	 */
	public boolean setLecturers(ArrayList<Lecture> lecturers)
	{
		// TODO - implement Module.setLecturers
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getStartData()
	{
		return this.startData;
	}

	/**
	 * 
	 * @param startData
	 */
	public void setStartData(LocalDateTime startData)
	{
		this.startData = startData;
	}

	public LocalDateTime getEndDate()
	{
		return this.endDate;
	}

	/**
	 * 
	 * @param endDate
	 */
	public boolean setEndDate(LocalDateTime endDate)
	{
		// TODO - implement Module.setEndDate
		throw new UnsupportedOperationException();
	}

	public ArrayList<Semester> getSemesters()
	{
		return this.semesters;
	}

	/**
	 * 
	 * @param semesters
	 */
	public boolean setSemesters(ArrayList<Semester> semesters)
	{
		// TODO - implement Module.setSemesters
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param semester
	 */
	public boolean addSemester(Semester semester)
	{
		// TODO - implement Module.addSemester
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param semester
	 */
	public boolean removeSemester(Semester semester)
	{
		// TODO - implement Module.removeSemester
		throw new UnsupportedOperationException();
	}

}