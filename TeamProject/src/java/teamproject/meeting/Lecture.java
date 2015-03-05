package teamproject.meeting;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import teamproject.user.people.Lecturer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.college.Module;
import teamproject.sql.SqlHandler;

public class Lecture extends Meeting {

	private String module;
	private ArrayList<Lecturer> lectures;

        public Lecture(Lecture other) 
        {
            this.title =
                    
                    other.title;
            this.description = other.description;
            this.id = other.id;
            this.hostUserID = other.hostUserID;
            this.people_attendees = other.people_attendees;
            this.group_attendees = other.group_attendees;
            this.groupId_attendees = other.groupId_attendees;
            this.peopleId_attendees = other.peopleId_attendees;
            this.length = other.length;
            this.startDateTime = other.startDateTime;
            this.endDateTime = other.endDateTime;
            this.runs_until = other.runs_until;
            this.location = other.location;
            this.recurring = other.recurring;
            this.repeatEvery = other.repeatEvery;
            this.priority = other.priority;
            this.privacy = other.privacy;
            this.module = other.module;
            this.lectures = other.lectures;

        }
    
        //String title, String description, int hostUserID, String peopleId_attendees, String groupId_attendees, int length, String runs_until, String location, int repeatEvery, int piority, int privacyId) 
    
        public Lecture( String title, String description, int hostUserID, int length, String runs_until, String location, int repeatEvery, int piority, int privacyId, String module)
        {
            super(title, description, hostUserID,  null,  null,length, runs_until,  location, repeatEvery, piority, privacyId); 
//            this.length = Duration.ofMinutes(length);
       
            this.module = module;
        }
	/**
	 * 
	 * @param lecture
	 */
	public boolean add(Lecture lecture)
	{
            // TODO - implement Lecture.add
            throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lecture
	 */
	public boolean remove(Lecture lecture)
	{
		// TODO - implement Lecture.remove
		throw new UnsupportedOperationException();
	}

	public String getModule()
	{
		return this.module;
	}

	/**
	 * 
	 * @param module
	 */
	public boolean setModule(Module module)
	{
		// TODO - implement Lecture.setModule
		throw new UnsupportedOperationException();
	}

	public String toSQL()
	{
		// TODO - implement Lecture.toSQL
		throw new UnsupportedOperationException();
	}
    
    public static boolean insertMeeting(Lecture meeting, String dateString)
    {
        String[] dates = dateString.split(",");
        Lecture[]  lectures = new Lecture[dates.length];
       // boolean[] success = new boolean[dates.length];
        String failed = "";
        int i = 0;
        for(String date: dates)
        {
            lectures[i] = new Lecture(meeting);
            if(!lectures[i].setTime(date))
            {
                failed += "Date " + date + " Could not be set.\n" ;
                System.err.println("FAILED TO SET DATE" + i);
            }   
            i++;
        }
        if(!failed.equals(""))
        {
            System.out.println(failed);
            return false;
        }
        for(Lecture m: lectures)
        {
            if(m == null)
            {
                System.out.println("Meetings is null");
            }
            InsertMeeting im = new InsertMeeting(m);
        }
        return true;
    }
    
    private static class InsertMeeting extends SqlHandler {
    
        protected PreparedStatement insertMeeting;
        protected PreparedStatement insertPeople;

        protected String meetingString  = "INSERT INTO `meeting` VALUES"
                        + "(null, ?,?,?,?,?,?,?,?,?,?);";
        protected String meetingGetID =
                "SELECT meeting_id FROM meeting "+ 
                "WHERE description = ?" +
                "AND location = ? " +
                "AND repeteEvery = ?" +
                "AND priority = ? " + 
                "AND start_time = ?" + 
                "AND end_time = ?" +
                "AND runs_until = ?";
        protected String peopleString;
        
        
        
        public InsertMeeting(Lecture meeting)
        {
            super();
            boolean meetingInserted = true; 
            try {
            //    this.connection.setAutoCommit(false);
                this.insertMeeting = connection.prepareStatement(meetingString);
                System.out.println((insertMeeting  == null) + " insertMeeting == null"+ meeting.getHostUserID() + meeting == null);
                insertMeeting.setInt(1, meeting.getHostUserID());
                insertMeeting.setString(2, meeting.getStartDateTime().toString());
                insertMeeting.setString(3, meeting.getEndDateTime().toString());
                insertMeeting.setString(4, meeting.getRuns_until().toString());
                insertMeeting.setString(5,meeting.getTitle());
                insertMeeting.setString(6,meeting.getDescription());
                insertMeeting.setInt(7, meeting.getPiority().getValue());
                insertMeeting.setString(8, meeting.getLocation());
                insertMeeting.setInt(9, meeting.getPrivacy().getId());
                insertMeeting.setString(10, meeting.getRepeatEvery().getPeriod().toString());


    //this.insertPeople = connection.prepareStatement(peopleString);
                
                int id = meeting.getId();
                System.out.println(id + ":ID");
                if(id == 0)
                {
                    insertMeeting.execute();
                    id = meeting.getIdFromDatabase();
                }
                System.out.println(id + ":ID ");
           
                String insertIntoHasMeeting = "INSERT INTO module_has_lecture VALUES "
                + "( (SELECT module_id FROM module WHERE code =\""+ meeting.module +"\"  ), " + meeting.id + ");";
                
                String insertLectureID = "INSERT INTO lecture values" +
                        "("+id +") ";
                this.runStatement(insertLectureID);
                System.out.println(insertIntoHasMeeting);
                this.runStatement(insertIntoHasMeeting);
                
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(InsertMeeting.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


        }

    }

}