package teamproject.user.people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.meeting.Meeting;
import teamproject.sql.SqlHandler;
import teamproject.system.Bugzilla;
import teamproject.system.SystemSetting;

/**
 * A Lecturer class which provides users who are lecturers with a means to schedule
 * meetings and labs.
 * 
 * @author Denise Lucey - 112700291
 */
public class Lecturer extends Staff {

	/**
	 * Schedules meeting
	 * @param meeting - details of meeting
	 */
	public boolean scheduleMeeting(Meeting meeting)
	{
		// TODO - implement Lecturer.scheduleMeeting
		throw new UnsupportedOperationException();
	}

        
	/**
	 * Schedules lab
	 * @param meeting - details of the meeting
	 */
	public boolean scheduleLab(Meeting meeting)
	{
		// TODO - implement Lecturer.scheduleLab
		throw new UnsupportedOperationException();
	}

}