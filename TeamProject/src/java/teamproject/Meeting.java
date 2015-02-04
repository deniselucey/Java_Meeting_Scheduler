package teamproject;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import teamproject.system.scheduler.timetable.TimeSlot;
import teamproject.user.Group;
import teamproject.user.Person;

public class Meeting {

	private String title;
	private String description;
	private int id;
	private HashSet<Person> people_attendees;
	private HashSet<Group> group_attendees;
	private Period length;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String location;
	private boolean recurring;
	private int numberOfRepeats;
	private Recurrence repeat;
	private MeetingType piority;

	/**
	 * 
	 * @param timeSlot
	 */
	public boolean confirm(TimeSlot timeSlot)
	{
		// TODO - implement Meeting.confirm
		throw new UnsupportedOperationException();
	}

	public String toSQL()
	{
		// TODO - implement Meeting.toSQL
		throw new UnsupportedOperationException();
	}

}