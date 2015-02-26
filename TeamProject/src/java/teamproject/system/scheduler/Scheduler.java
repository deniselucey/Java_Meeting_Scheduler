package teamproject.system.scheduler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import teamproject.meeting.Meeting;
import java.util.ArrayList;
import teamproject.meeting.Recurrence;
import teamproject.sql.SqlHandler;
import teamproject.system.scheduler.timetable.TimeSlot;
import teamproject.system.scheduler.timetable.Timetable;
import teamproject.user.people.Person;

public class Scheduler {
 
    private Meeting meetingToSchedule;
    private ArrayList<Integer> groupIds;
    private ArrayList<Integer> peopleIds;
    private LocalDate startOfRange;
    private LocalDate endOfRange;
    private ArrayList<Integer> meetingIds;
    private Recurrence recurrence;
    private SqlHandler sqlHandler;
    //private ResultSet sqlResults;
    private Timetable timetable;
    private ArrayList<Meeting> meetings;
    /**
     * Schedule a meeting from Now til meetings Runs until.
     * @param meetingToSchedule
     * @throws teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException 
     */
    public Scheduler(Meeting meetingToSchedule) throws RunUntilAfterEndRangeException, SQLException
    {
        this(meetingToSchedule,LocalDate.now(), meetingToSchedule.getRuns_until());
    }
    
    /**
     * Schedule a meeting from startOfRange til meetings Runs until.
     * @param meetingToSchedule
     * @param startOfRange
     * @throws teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException 
     */
    public Scheduler(Meeting meetingToSchedule, LocalDate startOfRange) throws RunUntilAfterEndRangeException, SQLException
    {
        this(meetingToSchedule,startOfRange, meetingToSchedule.getRuns_until());
    }
    
    /**
     * Schedule a once off or repeating meeting between the dates entered.
     * If meeting is recurring end OF range should be after meetings runs until.
     * @param meetingToSchedule
     * @param startOfRange
     * @param endOfRange 
     * @throws teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException 
     */
    public Scheduler(Meeting meetingToSchedule, LocalDate startOfRange, LocalDate endOfRange) throws RunUntilAfterEndRangeException, SQLException
    {
        this.endOfRange = endOfRange;
        if(meetingToSchedule.getRuns_until().isAfter(endOfRange))
        {
            throw new RunUntilAfterEndRangeException();
        }
        this.meetingToSchedule = meetingToSchedule;
        groupIds = new ArrayList<>();
        peopleIds = new ArrayList<>();
        //java Lamba expersions to get id of all groups and people attending, new to java 8 
        meetingToSchedule.getGroupId_attendees(this)
            .stream()
            .forEach(group -> groupIds.add(group));
        
        meetingToSchedule.getPeopleId_attendees(this)
            .stream()
            .forEach(people -> peopleIds.add(people));
        
        this.startOfRange = startOfRange;
        this.recurrence = meetingToSchedule.getRepeatEvery();
        this.sqlHandler = new SqlHandler();
        meetings = new ArrayList<>(); 
        this.loadMeeting();
        int weeks = (int) Math.ceil(Duration.between(startOfRange.atStartOfDay(),endOfRange.atStartOfDay()).toDays()/(double)7);
        this.timetable = new Timetable(this.startOfRange, weeks, meetings,1,1);
        System.out.println(timetable.toHTML());
        
        //LocalDate startDate, int weeks, ArrayList<Meeting> meetingWithRepeating
        
        
        //LocalDate startDate, int weeks, ArrayList<Meeting> meetingWithRepeating
    }

    
    public Scheduler(Meeting meetingToSchedule, LocalDate startOfRange, int weeks) throws RunUntilAfterEndRangeException, SQLException
    {   //LocalDate startDate, int weeks, ArrayList<Meeting> meetingWithRepeating
        this(meetingToSchedule, startOfRange, startOfRange.plusWeeks(weeks));
    }
    
    
    
    /**
     * 
     * @param meeting
     */
    public TimeSlot[] findSlots(Meeting meeting)
    {
            // TODO - implement Scheduler.findSlots
            throw new UnsupportedOperationException();
    }

    public ArrayList<Meeting> loadMeeting() throws SQLException
    {
        String sql = "SELECT * FROM meeting WHERE " +
                " meeting.start_time <= \"" + this.endOfRange + 
                "\" AND meeting.runs_until >= \"" + this.startOfRange +
                "\"\n AND (meeting_id IN ";
                
        String peopleAttending = "SELECT meeting_id \nFROM is_attending \nWHERE user_id IN (";
        boolean first = true;
        for(Integer id: this.peopleIds)
        {
            if(!first)
            {
                peopleAttending += ",";
            }
            peopleAttending += id;
            first = false;
        }
        peopleAttending += ")";
        sql += "\n(" +peopleAttending + ")"; 
        
        String groupAttending = "SELECT meeting_id FROM group_is_attending WHERE group_id IN (";
        first = true;
        for(Integer id: this.groupIds)
        {
            if(!first)
            {
                groupAttending += ",";       
            }
            groupAttending += id;
            first = false;
        }
        groupAttending += ")";
        
        sql += "OR meeting_id IN \n("+ groupAttending + "))";
        System.out.println("\n\n"+ sql +"\n\n");
        
        ResultSet sqlResults = sqlHandler.runQuery(sql);
        while(sqlResults.next())
        {
            meetings.add(new Meeting(sqlResults));
        }
        return meetings;
    }
    
    /**
     * 
     * @param meeting
     */
    public TimeSlot findSlot(Meeting meeting)
    {
            // TODO - implement Scheduler.findSlot
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param meeting
     */
    public boolean confirm(Meeting meeting)
    {
            // TODO - implement Scheduler.confirm
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param people
     */
    public Timetable getTimeTable(ArrayList<Person> people)
    {
            // TODO - implement Scheduler.getTimeTable
            throw new UnsupportedOperationException();
    }

    public void operation()
    {
            // TODO - implement Scheduler.operation
            throw new UnsupportedOperationException();
    }
    
    public static class RunUntilAfterEndRangeException extends Exception{}

}