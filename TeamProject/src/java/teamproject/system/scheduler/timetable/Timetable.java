package teamproject.system.scheduler.timetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;
import teamproject.meeting.Recurrence;
import teamproject.sql.SqlHandler;

public class Timetable {
    
    private TimeSlot[][] timeSlots;
    
    private  LocalDate startDate;
    private  LocalDate endDate;
    public static final int HOURS_IN_A_DAY = 10;
    public static final int MINUTES_IN_A_TIMESLOT = 15;
    public static final int NUMBER_OF_TIMESLOTS = (HOURS_IN_A_DAY *60) /MINUTES_IN_A_TIMESLOT ;
    private static final Duration DAY_START_TIME = Duration.ofHours(8);
    private static final Duration DAY_LEFT = Duration.ofDays(1).minusHours(Timetable.HOURS_IN_A_DAY);
    private int lengthInDays;
    private int hourRowSpan = 2;
    private int maxBlock = 4;
    
    public Timetable(int userId) throws SQLException{
        this(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue()-1), 1, loadAllMeeting(userId),1,4);
        //TODO Delete above line. Just for testing.    Well proper 1337!
    }
    
    
    public Timetable(int weeks,int userId) throws SQLException{
        this(LocalDate.of(2015, Month.JANUARY, 12), weeks, loadAllMeeting(userId),1,4);
        //TODO Delete above line. Just for testing.    Well proper 1337!
    }
    
    /**
     * 
     * @param startDate startDate of 
     * @param weeks 
     * @param endDate
     * @param rowspan
     * @param block
     * @param meetingWithRepeating 
     */
    public Timetable(LocalDate startDate, int weeks, ArrayList<Meeting> meetingWithRepeating,int rowspan, int block){
        this.startDate = startDate;
        System.out.println((this.startDate == null )+ " TimeTable range");
        
        this.endDate = startDate.plusWeeks(weeks);
        
        
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        lengthInDays = days;
        timeSlots = new TimeSlot[days][NUMBER_OF_TIMESLOTS];
        maxBlock = block;
        hourRowSpan = rowspan;
        
        ArrayList<Meeting> meetings = new ArrayList<>();
        if(meetingWithRepeating != null)
        {
            for(Meeting m: meetingWithRepeating){
                meetings.addAll(Meeting.expandMeeting(m, startDate, endDate));
            }
        }
        
        LocalDateTime timeSlotStartTime = startDate.atStartOfDay(); 
        timeSlotStartTime = timeSlotStartTime.plus(DAY_START_TIME);
        
        for(int i =0; i < timeSlots.length; i++){
            for(int j=0; j < timeSlots[i].length; j++){
                timeSlots[i][j] = new TimeSlot(timeSlotStartTime.plusMinutes((int)TimeSlot.getDuration().toMinutes()* j), i,j );
            }
            timeSlotStartTime = timeSlotStartTime.plusDays(1);
        }
        
        for(Meeting meeting : meetings){
                
                Duration duration = Duration.between(startDate.atStartOfDay(),meeting.getStartDateTime());
                int daysIndex = (int) duration.toDays();
                int minutes = (int) (duration.toMinutes() - (daysIndex*24*60) - DAY_START_TIME.toMinutes());
                int slotIndex = minutes / MINUTES_IN_A_TIMESLOT;
//                System.out.println(meeting);
                float length = ((float)meeting.getLength().toMinutes())/MINUTES_IN_A_TIMESLOT;
                //int slotOffset;
                for(int i = 0,slot = slotIndex ; i < length; i++){
                    
                    try{
                        timeSlots[daysIndex][slot].add(meeting);
                    } catch(Exception e){}
                    slot++;
                    if(slot == NUMBER_OF_TIMESLOTS)
                    {
//                        System.out.println("in loop");
//                        System.out.println(DAY_LEFT.toHours() * 60/TimeSlot.getDuration().toMinutes() );
                        i += DAY_LEFT.toHours() * 60/TimeSlot.getDuration().toMinutes() ;//
                        slot = 0 ;
                        daysIndex++;
                    }

                }
            
        }
    }
    
   
    public TimeSlot[][] getTimeSlots()
    {
        return this.timeSlots;
    }
    public String toHTML(boolean scheduling)
    {
        int daysInAWeek = 7;
        String htmlTable = "";
        LocalDate weekStart = this.startDate;
        int counter = 0;
        int days = timeSlots.length;
        //keeps track of when to print the next meeting of a day
        int[] timeSlotsFilled = new int[lengthInDays];

        while(weekStart.isBefore(endDate) && counter < days/7 )
        {

            htmlTable += "<table><tr>";
            htmlTable += "<th>" + weekStart.toString() + "</th>";
            //prints the days of the week across the top of a week
            for(int day = 0; day < daysInAWeek; day++ )
            {
                htmlTable += "<th>" + weekStart.plusDays(day).getDayOfWeek() + "</th>";
            }
            htmlTable += "</tr>";

            //print the body of the table
            for(int j=0; j < timeSlots[0].length; j++ )
            {
                htmlTable += "<tr>";
                //Prints the time in left most colume
                if(j % hourRowSpan ==0)
                {
                    htmlTable += "<th rowspan=\"" + hourRowSpan + "\" >" + formatDuration(DAY_START_TIME.plusMinutes(j*60/4)) + "</th>"; 
                }
                //print timeslots
                for(int i =0; i < daysInAWeek; i++)
                {
                    int dayIndex = counter*daysInAWeek + i;
                    if(timeSlotsFilled[dayIndex] <= j )
                    {
                    
                        int blockOf = 1;
                        int slotIndex = j;
                        //test if slotIndex is going to get a array out of bounds execption. 
                        // if current time slot is equal to the next one.
                        // Stops joining if its on an hour boundry.
                        while(slotIndex < NUMBER_OF_TIMESLOTS -1 && timeSlots[dayIndex][slotIndex].equals(timeSlots[dayIndex][slotIndex+1]) &&  (j + blockOf) % maxBlock != 0)
                        {
                            blockOf++;
                            slotIndex++;
                        }
                        //adds the timeslots meeting to the table with the rowspan of blockof
                        htmlTable  += scheduling?timeSlots[dayIndex][j].toSchedulerHTML():timeSlots[dayIndex][j].toHTML(blockOf);
                        //updates dept buffer to store the next slot to consider 
                        timeSlotsFilled[dayIndex] = timeSlotsFilled[dayIndex]+ blockOf;
                    }
                }
                htmlTable += "</tr>";
            }

            htmlTable += "</table>";
            weekStart = weekStart.plusWeeks(1);
            counter++;

        }
        return htmlTable;
    }
        
        
        
    /**
     * should not be part of finished product
     */
    private static ArrayList<Meeting> loadAllMeeting(int userId) throws SQLException
    {
        SqlHandler sh = new SqlHandler();
        ResultSet rs = sh.runQuery("SELECT * FROM meeting WHERE meeting_id in (SELECT meeting_id FROM is_attending WHERE user_id = '" + userId + "');");
//       ResultSet rs = sh.runQuery("SELECT * FROM meeting");
        ArrayList<Meeting> meetings = new ArrayList<>();
        while(rs.next())
        {
            meetings.add(new Meeting(rs));
        }
        return meetings;
    }

    public String formatDuration(Duration d)
    {
        return String.format("%02d:%02d", d.toHours(),d.toMinutes()%60);
    }

    public boolean setTimeSlotForScheduler(Meeting meeting)
    {
        Recurrence repeating= meeting.getRepeatEvery();
        if(repeating != Recurrence.NEVER)
        {
            int days = repeating.getPeriod().getDays();
            TimeSlot[][] newTimetable= new TimeSlot[days][NUMBER_OF_TIMESLOTS];
            
            int newTimeTableDaysIndex = 0;
            for(int i =0; i < timeSlots.length; i++)
            {
                newTimeTableDaysIndex = i % days;
//                System.out.println("DAys" + newTimeTableDaysIndex);
                for(int j=0; j < timeSlots[i].length; j++)
                {
                    if(newTimetable[newTimeTableDaysIndex][j] == null)
                    {
                        newTimetable[newTimeTableDaysIndex][j] = this.timeSlots[i][j];
                    }
                    else
                    {
                        newTimetable[newTimeTableDaysIndex][j].add(this.timeSlots[i][j]);
                    }    
                }
            }
            this.timeSlots = newTimetable;
        }
        for(int i =0; i < timeSlots.length; i++)
        {
            for(int j=0; j < timeSlots[i].length; j++)
            {
                if(!timeSlots[i][j].isFree())
                {
                    if(timeSlots[i][j].getMaxPriority() < meeting.getPiority().getValue())
                    {
                        timeSlots[i][j].setOveridable(true);
                    }
                    for(int k = 1; k < meeting.getLength().toMinutes() / TimeSlot.getDuration().toMinutes() && j - k >= 0; k++)
                    { 
                        timeSlots[i][j - k].setCantScheduleTo();
                    }
                }
            }
        }
        return true;
    }
}

