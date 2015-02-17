package teamproject.meeting;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import teamproject.system.scheduler.timetable.TimeSlot;
import teamproject.user.Group;
import teamproject.user.people.Person;

public class Meeting {

	private String title;
	private String description;
	private int id;
	private HashSet<Person> people_attendees;
	private HashSet<Group> group_attendees;
	private Duration length;
	private LocalDateTime startDate;
	private LocalDate endDate;
	private String location;
	private boolean recurring;
	private int numberOfRepeats;
	private Recurrence repeat;
	private MeetingType piority;
        private MeetingPrivacy privacy;

        
        public Meeting(int id)
        {
            
        }
        public Meeting(String title, String description, String location, Recurrence repeat, MeetingType piority, MeetingPrivacy privacy)
        {
            this.title = title;
            this.description = description;
            this.location = location;
            this.repeat = repeat;
            this.piority = piority;
            this.privacy = privacy;
        }
        
        public Meeting(String title, String description, String location, Recurrence repeat, MeetingType piority)
        {
            this.title = title;
            this.description = description;
            this.location = location;
            this.repeat = repeat;
            this.piority = piority;
        }
        
        public Meeting(String title, int meetingId, String description,int host_id, LocalDateTime start_time, LocalDateTime end_time, LocalDate runs_until,int priority, String location, boolean recurring, Recurrence repeat, MeetingPrivacy privacy, MeetingType piority)
        {
            this.title = title;
            this.description = description;
            this.id = meetingId;
//            HashSet<Person> people_attendees;
//            HashSet<Group> group_attendees;
            this.length = Duration.between(end_time, start_time);
            this.startDate = start_time;
            this.endDate = runs_until;
            this.location = location;
            this.recurring = end_time.equals(runs_until);
            this.repeat = repeat;
            this.numberOfRepeats = (int)(Duration.between(start_time, runs_until).toDays()/ repeat.getPeriod().getDays());
            this.piority = piority;
            this.privacy = privacy;
        }
        
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