package teamproject.system.scheduler;

import java.time.LocalDate;
import teamproject.meeting.Meeting;
import java.util.ArrayList;
import teamproject.meeting.Recurrence;
import teamproject.system.scheduler.timetable.TimeSlot;
import teamproject.system.scheduler.timetable.Timetable;
import teamproject.user.people.Person;

public class Scheduler {
 
    private Meeting meetingToSchedule;
    private ArrayList<Integer> groupIds;
    private ArrayList<Integer> peopleIds;
    private LocalDate startOfRange;
    private LocalDate endOfRange;
    private ArrayList<Integer> meetings;
    private Recurrence recurrence;

    
    /**
     * Schedule a meeting from Now til meetings Runs until.
     * @param meetingToSchedule
     * @throws teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException 
     */
    public Scheduler(Meeting meetingToSchedule) throws RunUntilAfterEndRangeException
    {
        this(meetingToSchedule,LocalDate.now(), meetingToSchedule.getRuns_until());
    }
    
    /**
     * Schedule a meeting from startOfRange til meetings Runs until.
     * @param meetingToSchedule
     * @param startOfRange
     * @throws teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException 
     */
    public Scheduler(Meeting meetingToSchedule, LocalDate startOfRange) throws RunUntilAfterEndRangeException
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
    public Scheduler(Meeting meetingToSchedule, LocalDate startOfRange, LocalDate endOfRange) throws RunUntilAfterEndRangeException
    {
        if(meetingToSchedule.getRuns_until().isBefore(endOfRange))
        {
            throw new RunUntilAfterEndRangeException();
        }
        this.meetingToSchedule = meetingToSchedule;
        groupIds = new ArrayList<>();
        peopleIds = new ArrayList<>();
        //java Lamba expersions to get id of all groups and people attending, new to java 8 
        meetingToSchedule.getGroup_attendees()
            .stream()
            .forEach(group -> groupIds.add(group.getId()));
        
        meetingToSchedule.getPeople_attendees()
            .stream()
            .forEach(people -> peopleIds.add(people.getId()));
        
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
        this.recurrence = meetingToSchedule.getRepeatEvery();
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

    private String loadMeeting()
    {
        String sql = "SELECT * FROM meeting WHERE " +
                " meeting.start_time <= " + this.endOfRange + 
                " AND meeting.runs_until >= " + this.startOfRange +
                "AND (meeting_id IN ";
                
        String peopleAttending = "SELECT meeting_id \nFROM is_attending \nWHERE user_id IN (";
        boolean first = true;
        for(Integer id: this.peopleIds)
        {
            if(!first)
            {
                peopleAttending += ",\n";
                first = false;
            }
            peopleAttending += id;
        }
        peopleAttending += ")";
        sql += "\n(" +peopleAttending + ")"; 
        
        
        String groupAttending = "SELECT meeting_id FROM group_is_attending WHERE group_id IN (";
        first = true;
        for(Integer id: this.groupIds)
        {
            if(!first)
            {
                groupAttending += ",\n";
                first = false;
            }
            groupAttending += id;
        }
        groupAttending += ")";
        
        sql += "OR meeting_id IN \n("+ groupAttending + "))";
        
        return sql;
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
    
    private class RunUntilAfterEndRangeException extends Exception{}

}