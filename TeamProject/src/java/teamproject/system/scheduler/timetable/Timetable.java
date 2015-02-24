package teamproject.system.scheduler.timetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import teamproject.meeting.Meeting;
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
    
    
    public Timetable() throws SQLException{
        this(LocalDate.of(2014, Month.DECEMBER, 29), 20, loadAllMeeting());
        //TODO Delete above line. Just for testing.    Well proper 1337!
    }
    
    /**
     * 
     * @param startDate startDate of 
     * @param endDate
     * @param meetingWithRepeating 
     */
    public Timetable(LocalDate startDate, int weeks, ArrayList<Meeting> meetingWithRepeating){
        this.startDate = startDate;
        this.endDate = startDate.plusWeeks(weeks);
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        lengthInDays = days;
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
        
        for(Meeting meeting : meetings){
                
                Duration duration = Duration.between(startDate.atStartOfDay(),meeting.getStartDateTime());
                int daysIndex = (int) duration.toDays();
                int minutes = (int) (duration.toMinutes() - (daysIndex*24*60) - DAY_START_TIME.toMinutes());
                int slotIndex = minutes / MINUTES_IN_A_TIMESLOT;

                float length = ((float)meeting.getLength().toMinutes())/MINUTES_IN_A_TIMESLOT;
                //int slotOffset;
                for(int i = 0,slot = slotIndex ; i < length; i++){
                    
                    try{
                        timeSlots[daysIndex][slot].add(meeting);
                    } catch(Exception e){}
                    slot++;
                    if(slot == NUMBER_OF_TIMESLOTS){
                        System.out.println("in loop");
                        i += DAY_LEFT.toHours() * TimeSlot.getDuration().toMinutes()/60 ;//
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
            int days = timeSlots.length;
            
            int[] timeSlotsFilled = new int[lengthInDays];
            //ArrayList<ArrayList<Integer>>[] colspans = new ArrayList<ArrayList<>>;
//            for(int i = 0; i < days ;i++ )
//            {
//                for(int j = 0; j < Timetable.NUMBER_OF_TIMESLOTS ;j++ )
//                {
//                    if()
//                }
//            }
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
                  if(j % hourRowSpan ==0)
                  {
                        htmlBuilder += "<th rowspan=\"" + hourRowSpan + "\" >" + formatDuration(DAY_START_TIME.plusMinutes(j*60/4)) + "</th>"; 
                  }
                    
                    
                    
                    for(int i =0; i < daysInAWeek; i++){
                        int dayIndex = counter*daysInAWeek + i;
                        if(timeSlotsFilled[dayIndex] <= j )
                        {
                            //try{
                                int blockOf = 1;
                                int slotIndex = j;
                                //test if slotIndex is going to get a array out of bounds execption. 
                                // if current time slot is equal to the next one.
                                // Stops joinng if its on an hour boundry.
                                while(slotIndex < NUMBER_OF_TIMESLOTS -1 && timeSlots[dayIndex][slotIndex].equals(timeSlots[dayIndex][slotIndex+1]) &&  (j + blockOf) % 4 != 0)//j + blockOf%4 != 0)
                                {
                                    blockOf++;
                                    slotIndex++;
                                }
                                htmlBuilder  += timeSlots[dayIndex][j].toHTML(blockOf);
                                //updates dept buffer to store the next slot to consider 
                                timeSlotsFilled[dayIndex] = timeSlotsFilled[dayIndex]+ blockOf;
                            //} catch(ArrayIndexOutOfBoundsException e){}
                        
                        }
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
        
        
        
        /**
         * should not be part of finished product
         */
        private static ArrayList<Meeting> loadAllMeeting() throws SQLException
        {
            SqlHandler sh = new SqlHandler();
            ResultSet rs = sh.runQuery("SELECT * FROM meeting");
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
}

