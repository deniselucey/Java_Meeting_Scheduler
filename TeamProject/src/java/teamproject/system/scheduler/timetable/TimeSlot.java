package teamproject.system.scheduler.timetable;

import teamproject.meeting.Meeting;
import java.time.Period;
import java.util.HashSet;

public class TimeSlot {

	private Period timeOffset;
	private HashSet<Meeting> meeting;
	private int[][] collision;

}