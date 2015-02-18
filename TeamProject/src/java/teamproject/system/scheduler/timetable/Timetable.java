package teamproject.system.scheduler.timetable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;
import java.util.*;

public class Timetable {
    
    /*private TimeSlot[][] timeSlots;
    
    private  LocalDate startDate;
    private  LocalDate endDate;
    public final int NUMBER_OF_TIMESLOTS = 40;*/
    
   
    
    /*public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<Meeting> meetings){
        this.startDate = startDate;
        this.endDate = endDate;
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        timeSlots = new TimeSlot[days][NUMBER_OF_TIMESLOTS];
        
       /**  for(Meeting meeting : meetings){
            timeSlots.add(meeting);
        }**/
    
    
    
   // public String details(){
        // contains a query that will pull details and this will be called in the constructor.
        
   // } */






        public String TabletoHTML(){
            
            String htmlBuilder = "";
            
            htmlBuilder += "<table>";
            htmlBuilder += "<th> Monday </th>";
            htmlBuilder += "<th> Tuesday </th>";
            htmlBuilder += "<th> Wednesday </th>";
            htmlBuilder +="<th> Thursday </th>";
            htmlBuilder +="<th> Friday </th>";
            htmlBuilder += "</table>";
            
            return htmlBuilder;
	}
}

