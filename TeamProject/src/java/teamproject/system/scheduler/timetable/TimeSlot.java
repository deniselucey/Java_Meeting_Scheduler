package teamproject.system.scheduler.timetable;

import teamproject.meeting.Meeting;
import java.util.HashSet;


import javafx.util.Duration;




  

public class TimeSlot {

	private final Duration timeOffset = Duration.minutes(15);
	private HashSet<Meeting> meetings;

        
        public TimeSlot(){
            meetings= new HashSet<>();
            
            
            
        }
        
        
        public void add(Meeting meeting){
            meetings.add(meeting);
        }
        
//        public static void main(String[] args) {
//            
//            ArrayList<Timetable> table = new ArrayList<Timetable>();
//             
//        try
//        {
//            
//             
//         SqlHandler sqlh = new SqlHandler();
//          ResultSet rs =   sqlh.runQuery("SELECT meeting_id, DAYNAME(DATE(start_time)), DATE(start_time),TIME(end_time) FROM Meeting WHERE meeting_id IN ( SELECT meeting_id FROM Is_Attending WHERE user_id = '1');");
//            while(rs.next())
//            {
//                System.out.println(rs.getString(1));    //First Column
//                System.out.println(rs.getString(2));    //Second Column
//                System.out.println(rs.getString(3));    //Third Column
//                System.out.println(rs.getString(4));    //Fourth Column
//                
//                
//               
//                table.add(new Timetable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
//                
//                
//            }
//            
//            for(Timetable row : table){
//                row.testTable();
//            }
//        }
//        catch (Exception e) {
//            
//        }
//    }
}
