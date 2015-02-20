package teamproject.system.scheduler.timetable;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;
import teamproject.meeting.Recurrence;

public class Timetable {
    
    private TimeSlot[][] timeSlots;
    
    private  LocalDate startDate;
    private  LocalDate endDate;
    public final int HOURS_IN_A_DAY = 10;
    public final int MINUTES_IN_A_TIMESLOT = 15;
    public final int NUMBER_OF_TIMESLOTS = (HOURS_IN_A_DAY *60) /MINUTES_IN_A_TIMESLOT ;
    private final Duration DAY_START_TIME = Duration.ofHours(8);
    
    public Timetable() throws SQLException{
        this(LocalDate.of(2015, Month.JANUARY, 21), LocalDate.of(2015, Month.JANUARY, 26), new ArrayList<Meeting>(){{add(new Meeting(1));add(new Meeting(2));add(new Meeting(3));}});
        //TODO Delete above line. Just for testing.    Well proper 1337!
    }
    
   
    
    public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<Meeting> meetings){
        this.startDate = startDate;
        this.endDate = endDate;
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        timeSlots = new TimeSlot[days][NUMBER_OF_TIMESLOTS];
        
        for(int i =0; i < timeSlots.length; i++){
            for(int j=0; j < timeSlots[i].length; j++){
                timeSlots[i][j] = new TimeSlot();
            }
        }
        
        //
        for(Meeting meeting : meetings){
            if(meeting.getRepeatEvery() == Recurrence.NEVER){
                 meeting.getRepeatEvery();
                 Duration duration = Duration.between(startDate.atStartOfDay(),meeting.getStartDateTime());
                 int daysIndex = (int) duration.toDays();
                 int minutes = (int) (duration.toMinutes() - (daysIndex*24*60) - DAY_START_TIME.toMinutes());
                 int slotIndex = minutes / MINUTES_IN_A_TIMESLOT;
                 
                 float length = ((float)meeting.getLength().toMinutes())/MINUTES_IN_A_TIMESLOT;
                 for(int i = 0 ; i < length; i++){
                    timeSlots[daysIndex][slotIndex + i].add(meeting);
                 }
            }
           
        }
    }
    
    





        public String toHTML(){
            
            String htmlBuilder = "";
            
            htmlBuilder += "<table><tr>";
            htmlBuilder += "<th> Monday </th>";
            htmlBuilder += "<th> Tuesday </th>";
            htmlBuilder += "<th> Wednesday </th>";
            htmlBuilder +="<th> Thursday </th>";
            htmlBuilder +="<th> Friday </th></tr>";
            
            for(int j=0; j < timeSlots[0].length; j++ ){
                htmlBuilder += "<tr>";
                for(int i =0; i < timeSlots.length; i++){
                    htmlBuilder  += timeSlots[i][j].toHTML();
                }
                htmlBuilder += "</tr>";
            }
            
            htmlBuilder += "</table>";
            
            return htmlBuilder;
	}
}

