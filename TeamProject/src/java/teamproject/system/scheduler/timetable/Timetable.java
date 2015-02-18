package teamproject.system.scheduler.timetable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;
import java.util.*;

public class Timetable {
    
    private TimeSlot[][] timeSlots;
    
    private  LocalDate startDate;
    private  LocalDate endDate;
    public final int NUMBER_OF_TIMESLOTS = 40;
    
    public Timetable(){
        
    }
    
    public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<Meeting> meetings){
        this.startDate = startDate;
        this.endDate = endDate;
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        timeSlots = new TimeSlot[days][NUMBER_OF_TIMESLOTS];
        
       /**  for(Meeting meeting : meetings){
            timeSlots.add(meeting);
        }**/
    }
    
    
   // public String details(){
        // contains a query that will pull details and this will be called in the constructor.
        
   // }

	public String TabletoHTML(){
            
            
            StringBuilder htmlBuilder = new StringBuilder();
            
            htmlBuilder.append("<table>");
            htmlBuilder.append("<th> Monday </th>");
            htmlBuilder.append("<th> Tuesday </th>");
            htmlBuilder.append("<th> Wednesday </th>");
            htmlBuilder.append("<th> Thursday </th>");
            htmlBuilder.append("<th> Friday </th>");
            htmlBuilder.append("</table>");
            
            System.out.println(htmlBuilder);
            
           
            return htmlBuilder.toString();
	}

	
         
        

}