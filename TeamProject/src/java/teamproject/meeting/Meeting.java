package teamproject.meeting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import teamproject.system.scheduler.timetable.TimeSlot;
import teamproject.user.Group;
import teamproject.user.people.Person;

public class Meeting {

    private String title;
    private String description;
    private int id;
    private int hostUserID;
    private HashSet<Person> people_attendees;
    private HashSet<Group> group_attendees;
    private Duration length;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDate runs_until;
    private String location;
    private boolean recurring;
    private Recurrence repeatEvery;
    private byte piority;
    private MeetingPrivacy privacy;
    private static final String sqlSelect = "SELECT * FROM meeting ";

    public Meeting(int id) throws SQLException
    {
        SqlHandler sqlHandler = new SqlHandler();
        ResultSet rs = sqlHandler.runQuery(sqlSelect + "WHERE meeting_id = " + id + ";");

        if(rs.next())
        {
            this.id = rs.getInt("meeting_id");
            title = rs.getString("meeting_name");
            description = rs.getString("description");
            //	private HashSet<Person> people_attendees;
            //	private HashSet<Group> group_attendees;
            this.hostUserID = rs.getInt("host_id");
            startDateTime = sqlDateStringToLocalDateTime(rs.getString("start_time"));
            endDateTime = sqlDateStringToLocalDateTime(rs.getString("end_time"));
            runs_until  = LocalDate.parse(rs.getString("runs_until"));
            location = rs.getString("location");
            repeatEvery = Recurrence.findByPeriod( Period.parse(rs.getString("repeteEvery")));
            piority =  (byte)rs.getInt("priority");
            privacy  =  MeetingPrivacy.values()[rs.getInt("privacy_id")];
            length = Duration.between(startDateTime, endDateTime);
        }  
    }

    public Meeting(String title, String description, String location, Recurrence repeat, MeetingType piority, MeetingPrivacy privacy)
    {
        this.title = title;
        this.description = description;
        this.location = location;
        this.repeatEvery = repeat;
        this.piority = piority.getPriority();
        this.privacy = privacy;
    }
    
    public Meeting(ResultSet rs) throws SQLException
    {
        title = rs.getString("meeting_name");
        description = rs.getString("desciption");
        id = rs.getInt("meeting_id");
        hostUserID =  rs.getInt("host_id");
        startDateTime = sqlDateStringToLocalDateTime(rs.getString("start_time"));
        endDateTime = sqlDateStringToLocalDateTime(rs.getString("end_time"));
        runs_until = LocalDate.parse(rs.getString("runs_until"));
        location = rs.getString("location");
        repeatEvery = Recurrence.findByPeriod(Period.parse(rs.getString("repeteEvery")));
        recurring = repeatEvery != Recurrence.NEVER;
        piority = rs.getByte("priority"); 
        privacy = MeetingPrivacy.getMeetingPrivacyByID(rs.getInt("privacy_id"));
        length = Duration.between(startDateTime, endDateTime);
    }
    
    public Meeting(String title, String description,
            int host_id, LocalDateTime start_time, LocalDateTime end_time,
            LocalDate runs_until, String location, 
            boolean recurring, Recurrence repeat, MeetingPrivacy privacy, 
            MeetingType piority, int hostId)
    {
        this.title = title;
        this.description = description;
        this.length = Duration.between(end_time, start_time);
        this.startDateTime = start_time;
        this.endDateTime = end_time;
        this.runs_until = runs_until;
        this.location = location;
        this.recurring = end_time.toLocalDate().compareTo(runs_until) == -1;
        this.repeatEvery = repeat;
        this.piority = piority.getPriority();
        this.privacy = privacy;
        this.hostUserID = hostId;
    }

    /**
     * Add start Time and end time and repeat until to this meeting. Add it to the database
     * @param timeSlot
     */
    public boolean confirm(TimeSlot timeSlot)
    {
            // TODO - implement Meeting.confirm
            throw new UnsupportedOperationException();
    }

    public String toSQL()
    {
        String meetingSql="";
        if(this.id == 0)
        {       meetingSql = "INSERT INTO `meeting` VALUES"
                + "(null, " +
                hostUserID + ",'" +
                startDateTime.toString() +"','"+
                endDateTime.toString() +"','"+
                runs_until.toString()  +"','"+
                title  +"','"+ description  +"',"+
                piority  +",'"+ location  +"',"+
                privacy.getId()  +",'"+
                this.repeatEvery.getPeriod().toString() +
                "');";
        }
        else
        {
            meetingSql = "UPDATE meeting"
                    + "SET host_id " + this.getHostUserID()
                    + ",start_time '" + this.getStartDateTime().toString() 
                    + "', end_time '" + this.getEndDateTime().toString()
                    + "', runs_until '" + this.getRuns_until()
                    + "', meeting_name '" + this.getTitle()
                    + "', description '" + this.getDescription()
                    + "', priority " + this.getPiority()
                    + ", location '" + this.getLocation()
                    + "', privacy_id '" + this.getPrivacy().toString() 
                    + "', repeteEvery '" + this.getRepeatEvery().getPeriod().toString() + "');";
        }
        return meetingSql;
    }

    /**
     * Obtain meeting id from database using all other attributes to search database.
     * 
     * @return id of meeting or -1 if not in database
     * @throws SQLException 
     */
    public int getIdFromDatabase() throws SQLException
    {
        int result = -1;
        String getMeetingId = "SELECT meeting_id FROM meeting "
            + "WHERE meeting_name = '" + this.title + "' " + 
            "AND description = '" + this.description + "' " +
            "AND location = '" + this.location + "' " +
            "AND repeteEvery = '" + this.repeatEvery.getPeriod().toString()+"' " +
            "AND priority = " + this.piority + 
            " AND start_time = '"+ this.startDateTime.toString()+"'" + 
            " AND end_time = '"+ this.endDateTime.toString()+"'" +
            " AND runs_until = '"+ this.runs_until.toString()+"';";
 
        SqlHandler sqlh = new SqlHandler();
        ResultSet resultSet = sqlh.runQuery(getMeetingId);
        if(resultSet.next())
        {
            
            this.id = resultSet.getInt("meeting_id");
            result = id;
        }
        return result;
    }
        
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getHostUserID() {
        return hostUserID;
    }

    public HashSet<Group> getGroup_attendees() {
        return group_attendees;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDate getRuns_until() {
        return runs_until;
    }

    public String getLocation() {
        return location;
    }

    public Recurrence getRepeatEvery() {
        return repeatEvery;
    }

    public byte getPiority() {
        return piority;
    }

    public MeetingPrivacy getPrivacy() {
        return privacy;
    }

    public HashSet<Person> getPeople_attendees() {
        return people_attendees;
    }

    public Duration getLength() {
        return length;
    }
        
    @Override
    public String toString() 
    {
        return "Meeting{" + "title=" + title + ", description=" + description + ", id=" + id + ", hostUserID=" + hostUserID + ", people_attendees=" + people_attendees + ", group_attendees=" + group_attendees + ", length=" + length + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", runs_until=" + runs_until + ", location=" + location + ", recurring=" + recurring + ", repeatEvery=" + repeatEvery + ", piority=" + piority + ", privacy=" + privacy + '}';
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.hostUserID;
        hash = 47 * hash + Objects.hashCode(this.people_attendees);
        hash = 47 * hash + Objects.hashCode(this.group_attendees);
        hash = 47 * hash + Objects.hashCode(this.length);
        hash = 47 * hash + Objects.hashCode(this.startDateTime);
        hash = 47 * hash + Objects.hashCode(this.endDateTime);
        hash = 47 * hash + Objects.hashCode(this.runs_until);
        hash = 47 * hash + Objects.hashCode(this.location);
        hash = 47 * hash + (this.recurring ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.repeatEvery);
        hash = 47 * hash + this.piority;
        hash = 47 * hash + Objects.hashCode(this.privacy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meeting other = (Meeting) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.hostUserID != other.hostUserID) {
            return false;
        }
        if (!Objects.equals(this.people_attendees, other.people_attendees)) {
            return false;
        }
        if (!Objects.equals(this.group_attendees, other.group_attendees)) {
            return false;
        }
        if (!Objects.equals(this.length, other.length)) {
            return false;
        }
        if (!Objects.equals(this.startDateTime, other.startDateTime)) {
            return false;
        }
//        if (!Objects.equals(this.endDateTime, other.endDateTime)) {
//            return false;
//        }
//        if (!Objects.equals(this.runs_until, other.runs_until)) {
//            return false;
//        }
//        if (!Objects.equals(this.location, other.location)) {
//            return false;
//        }
//        if (this.recurring != other.recurring) {
//            return false;
//        }
//        if (this.repeatEvery != other.repeatEvery) {
//            return false;
//        }
//        if (this.piority != other.piority) {
//            return false;
//        }
//        return this.privacy == other.privacy;
        return true;
    }
   
    
    private class InsertMeeting extends SqlHandler {
    
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

        public InsertMeeting(Meeting meeting)
        {
            super();
            boolean meetingInserted = true; 
            try {
                this.connection.setAutoCommit(false);
                this.insertMeeting = connection.prepareStatement(meetingString);
                insertMeeting.setInt(1, meeting.getHostUserID());
                insertMeeting.setString(2, meeting.getStartDateTime().toString());
                insertMeeting.setString(3, meeting.getEndDateTime().toString());
                insertMeeting.setString(4, meeting.getRuns_until().toString());
                insertMeeting.setString(5,meeting.getTitle());
                insertMeeting.setString(6,meeting.getDescription());
                insertMeeting.setInt(7, meeting.getPiority());
                insertMeeting.setString(8, meeting.getLocation());
                insertMeeting.setInt(9, meeting.getPrivacy().getId());
                insertMeeting.setString(10, meeting.getRepeatEvery().getPeriod().toString());


    //this.insertPeople = connection.prepareStatement(peopleString);
                int id = meeting.getId();
                if(id == 0)
                {
                    insertMeeting.execute();
                    id = meeting.getIdFromDatabase();
                }

                String personAttending = "INSERT INTO is_attending VALUES ";
                HashSet<Person> people = meeting.getPeople_attendees();
                int length = people.size();
                for(Person person:people)
                {
                    personAttending += "(" + id + ", " +  person.getId() + ")" ;
                    --length;
                    personAttending += length == 0?";":",";  
                }

                String groupAttending = "INSERT INTO group_is_attending VALUES ";
                HashSet<Group> groups = meeting.getGroup_attendees();
                length = groups.size();
                for(Group group : groups)
                {
                    groupAttending += "(" + id + ", " +  group.getId() + ")" ;
                    --length;
                    groupAttending += length == 0?";":",";  
                }

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
    
    /**
     * Parses a SQL DATETIME String and turns it into a LocalDateTime.
     * @param sqlDateString
     * @return 
     */
    public static LocalDateTime sqlDateStringToLocalDateTime(String sqlDateString)
    {
        int truncateDateLength = 19;
        
        String truncate = sqlDateString.subSequence(0, truncateDateLength).toString();
        char[] sqlChars = truncate.toCharArray();
        sqlChars[10] = 'T';
        return LocalDateTime.parse(String.valueOf(sqlChars));
    }    
}