package teamproject.system.scheduler.timetable;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;

public class Timetable {
    
    private TimeSlot[][] timeSlots;
    
    private  LocalDate startDate;
    private  LocalDate endDate;
    public static final int HOURS_IN_A_DAY = 10;
    public static final int MINUTES_IN_A_TIMESLOT = 15;
    public static final int NUMBER_OF_TIMESLOTS = (HOURS_IN_A_DAY *60) /MINUTES_IN_A_TIMESLOT ;
    private static final Duration DAY_START_TIME = Duration.ofHours(8);
    
    public Timetable() throws SQLException{
        this(LocalDate.of(2015, Month.JANUARY, 5), LocalDate.of(2015, Month.JANUARY, 5).plusWeeks(20), new ArrayList<Meeting>(){{add(new Meeting(1));add(new Meeting(2));add(new Meeting(3));add(new Meeting(4));add(new Meeting(5));add(new Meeting(6));add(new Meeting(7));add(new Meeting(8));add(new Meeting(9));add(new Meeting(10));add(new Meeting(11));add(new Meeting(12));add(new Meeting(13));add(new Meeting(14));add(new Meeting(15));add(new Meeting(16));add(new Meeting(17));}});
        //TODO Delete above line. Just for testing.    Well proper 1337!
    }
    
    public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<Meeting> meetingWithRepeating){
        this.startDate = startDate;
        this.endDate = endDate;
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        
//        System.out.println( "DAYS:" + days);
        timeSlots = new TimeSlot[days][NUMBER_OF_TIMESLOTS];
        
        ArrayList<Meeting> meetings = new ArrayList<>();
        
        for(Meeting m: meetingWithRepeating){
            meetings.addAll(Meeting.expandMeeting(m, startDate, endDate));
        }
        
        
        for(int i =0; i < timeSlots.length; i++){
            for(int j=0; j < timeSlots[i].length; j++){
                timeSlots[i][j] = new TimeSlot();
            }
        }
        
        //
        for(Meeting meeting : meetings){
                
                Duration duration = Duration.between(startDate.atStartOfDay(),meeting.getStartDateTime());
                int daysIndex = (int) duration.toDays();
                int minutes = (int) (duration.toMinutes() - (daysIndex*24*60) - DAY_START_TIME.toMinutes());
                int slotIndex = minutes / MINUTES_IN_A_TIMESLOT;

                float length = ((float)meeting.getLength().toMinutes())/MINUTES_IN_A_TIMESLOT;
                //int slotOffset;
                for(int i = 0,slot = slotIndex ; i < length; i++){
                
                    //System.out.println("boolean :  " +((slotIndex + slot)%NUMBER_OF_TIMESLOTS) + "  i:" + slot + " daysIndex " + "daysIndex:" +daysIndex+"  slotOffset:"+slotOffset );
                   //System.out.println("daysIndex:" +daysIndex+"  slotOffset:"+slotOffset );
                    try{
                        timeSlots[daysIndex][slot].add(meeting);
                    } catch(Exception e){}
                    slot++;
                    if(slot == NUMBER_OF_TIMESLOTS){
                        System.out.println("in loop");
                        i += 14 * 4 - 1;
                        slot = 0 ;
                        daysIndex++;
                    }
                }
            
        }
    }
    
   
        public String toHTML(){
            int daysInAWeek = 7;
            String htmlBuilder = "";
            LocalDate weekStart = this.startDate;
            int counter = 0;
            while(weekStart.isBefore(endDate))
            {
               
                htmlBuilder += "<table><tr>";
                htmlBuilder += "<th>" + weekStart.toString() + "</th>";
                for(int day = 0; day < daysInAWeek; day++ )
                {
                    htmlBuilder += "<th>" + weekStart.plusDays(day).getDayOfWeek() + "</th>";
                }
                htmlBuilder += "</tr>";
                
                for(int j=0; j < timeSlots[0].length; j++ ){
                    htmlBuilder += "<tr>";
                    if(j % 4 ==0)
                    {htmlBuilder += "<th rowspan=\"4\">" + (DAY_START_TIME.toHours() + (j/4)) + ":00</th>";}
                    for(int i =0; i < daysInAWeek; i++){
//                       System.out.println("counter"+  counter  +" i " + i + "index" +( counter*daysInAWeek + i));
                        //System.out.println("days"+(counter*daysInAWeek + i) +" J: "+j);
                        try{
                        htmlBuilder  += timeSlots[counter*daysInAWeek + i][j].toHTML();
                        } catch(ArrayIndexOutOfBoundsException e){}
                    }
                    htmlBuilder += "</tr>";
                }

                htmlBuilder += "</table>";
                weekStart = weekStart.plusWeeks(1);
                counter++;
//                System.out.println("TIMES through loop"+ counter);
            }
            return htmlBuilder;
	}
}

