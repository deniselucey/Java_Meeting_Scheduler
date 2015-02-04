package teamproject.system.scheduler.timetable;

import java.time.Period;
import java.util.HashSet;
import teamproject.*;

public class TimeSlot {

	private Period timeOffset;
	private HashSet<Meeting> meeting;
	private int[][] collision;

}