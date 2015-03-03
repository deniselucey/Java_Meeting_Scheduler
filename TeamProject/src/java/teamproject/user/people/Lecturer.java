package teamproject.user.people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.meeting.Meeting;
import teamproject.sql.SqlHandler;
import teamproject.system.Bugzilla;
import teamproject.system.SystemSetting;

public class Lecturer extends Staff {
        private static boolean createdModule = false;
        private static boolean deleteModule = false;
        private static boolean editModule = false;

	/**
	 * 
	 * @param meeting
	 */
	public boolean scheduleMeeting(Meeting meeting)
	{
		// TODO - implement Lecturer.scheduleMeeting
		throw new UnsupportedOperationException();
	}

        
	/**
	 * 
	 * @param meeting
	 */
	public boolean scheduleLab(Meeting meeting)
	{
		// TODO - implement Lecturer.scheduleLab
		throw new UnsupportedOperationException();
	}

}