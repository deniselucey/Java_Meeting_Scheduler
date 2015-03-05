package teamproject.meeting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import teamproject.system.Email;
import teamproject.system.scheduler.Scheduler;
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
    private Priority priority;
    private MeetingPrivacy privacy;
    private HashSet<Integer> peopleId_attendees;
    private HashSet<Integer> groupId_attendees;
    
    
    private static final String sqlSelect = "SELECT * FROM meeting ";
    
    public Meeting(){
        
    }
    

    public Meeting(int id) throws SQLException
    {
        SqlHandler sqlHandler = new SqlHandler();
        ResultSet rs = sqlHandler.runQuery(sqlSelect + "WHERE meeting_id = " + id + ";");

        if(rs.next())
        {
            this.id = rs.getInt("meeting_id");
            title = rs.getString("meeting_name");
            description = rs.getString("description");
            people_attendees = new HashSet<>();
            group_attendees = new HashSet<>();
            this.hostUserID = rs.getInt("host_id");
            startDateTime = sqlDateStringToLocalDateTime(rs.getString("start_time"));
            endDateTime = sqlDateStringToLocalDateTime(rs.getString("end_time"));
            runs_until  = LocalDate.parse(rs.getString("runs_until"));
            location = rs.getString("location");
            repeatEvery = Recurrence.findByPeriod( Period.parse(rs.getString("repeteEvery")));
            priority =  Priority.getPriorityByValue(rs.getInt("priority"));
            privacy  =  MeetingPrivacy.getMeetingPrivacyByID(rs.getInt("privacy_id"));
            length = Duration.between(startDateTime, endDateTime);
            peopleId_attendees = new HashSet<>();
            ResultSet peopleRs = sqlHandler.runQuery("SELECT user_id FROM is_attending WHERE meeting_id =" + this.id+";");
            while(peopleRs.next() )
            {
                System.out.println("SELECT PEOPELSRS " + peopleRs.getInt("user_id"));
                peopleId_attendees.add(peopleRs.getInt("user_id"));
            }
        }  
    }

    public Meeting(String title, String description, String location, Recurrence repeat, Priority piority, MeetingPrivacy privacy)
    {
        this.title = title;
        this.description = description;
        this.location = location;
        this.repeatEvery = repeat;
        this.priority = piority;
        this.privacy = privacy;
        people_attendees = new HashSet<>();
        group_attendees = new HashSet<>();
    }
    
    public Meeting(ResultSet rs) throws SQLException
    {
        title = rs.getString("meeting_name");
        description = rs.getString("description");
        id = rs.getInt("meeting_id");
        hostUserID =  rs.getInt("host_id");
        startDateTime = sqlDateStringToLocalDateTime(rs.getString("start_time"));
        endDateTime = sqlDateStringToLocalDateTime(rs.getString("end_time"));
        runs_until = LocalDate.parse(rs.getString("runs_until"));
        location = rs.getString("location");
        repeatEvery = Recurrence.findByPeriod(Period.parse(rs.getString("repeteEvery")));
        recurring = repeatEvery != Recurrence.NEVER;
        priority = Priority.getPriorityByValue(rs.getByte("priority")); 
        privacy = MeetingPrivacy.getMeetingPrivacyByID(rs.getInt("privacy_id"));
        length = Duration.between(startDateTime, endDateTime);
        people_attendees = new HashSet<>();
        group_attendees = new HashSet<>();
    }

    public Meeting(Meeting other) {
        this.title = other.title;
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
    }
    
    
    
    public Meeting(String title, String description,
            int host_id, LocalDateTime start_time, LocalDateTime end_time,
            LocalDate runs_until, String location, 
            boolean recurring, Recurrence repeat, MeetingPrivacy privacy, 
           Priority piority, int hostId)
    {
        this.title = title;
        this.description = description;
        this.length = Duration.between(start_time, end_time);
        this.startDateTime = start_time;
        this.endDateTime = end_time;
        this.runs_until = runs_until;
        this.location = location;
        
        this.repeatEvery = repeat;
        this.recurring = repeat != Recurrence.NEVER;
        this.priority = piority;
        this.privacy = privacy;
        this.hostUserID = hostId;
        people_attendees = new HashSet<>();
        group_attendees = new HashSet<>();
    }
    /**
     *  This is should be called by the meetings site 
     * @param title
     * @param description
     * @param hostUserID
     * @param peopleId_attendees
     * @param groupId_attendees
     * @param length
     * @param startDateTime
     * @param runs_until
     * @param location
     * @param repeatEvery
     * @param piority
     * @param privacy 
     */
    public Meeting(String title, String description, int hostUserID, String peopleId_attendees, String groupId_attendees, int length, String runs_until, String location, int repeatEvery, int piority, int privacyId) 
    {
        this.title = title;
        this.description = description;
        this.hostUserID = hostUserID;
//        this.people_attendees = people_attendees;
//        this.group_attendees = group_attendees;
        this.length = Duration.ofMinutes(length);
        this.runs_until = LocalDate.parse(runs_until);
        this.location = location;
        this.repeatEvery = Recurrence.values()[repeatEvery];
        this.priority = Priority.getPriorityByValue(piority);
        this.privacy = MeetingPrivacy.getMeetingPrivacyByID(privacyId);
        this.recurring = this.repeatEvery != Recurrence.NEVER;
        this.peopleId_attendees = new HashSet<>();
        this.groupId_attendees = new HashSet<>();
        if(groupId_attendees != null && !groupId_attendees.equals("")){
            String[] groupId = groupId_attendees.split(",");
            Arrays.stream(groupId).forEach(i -> this.groupId_attendees.add(Integer.parseInt(i)));
        }
        if(peopleId_attendees != null && !peopleId_attendees.equals(""))
        {
            
            String[] peopleId = peopleId_attendees.split(",");
            Arrays.stream(peopleId).filter(i -> i !=null && !i.equals("")).forEach(i -> this.peopleId_attendees.add(Integer.parseInt(i)));
        }
    }

    
    /**
     * Add start Time and end time and repeat until to this meeting. Add it to the database
     * @param timeSlot
     */

    /**
     * Add start Time and end time and repeat until to this meeting.Add it to the database
     * @param timeSlot
     * @return
     */
    public boolean setTime(LocalDateTime time)
    {
        if(!checkCollisions())
        {
            this.startDateTime = time;
            this.endDateTime = startDateTime.plus(this.length);
            return true;
        }
        return false;
    }
    
    public boolean setTime(String time)
    {
            // TODO - implement Meeting.confirm
        LocalDateTime dateTime;
        try{
            dateTime= LocalDateTime.parse(time);
            return setTime(dateTime);
        } 
        catch(Exception e){return false;}
            
    }

    public boolean checkCollisions()
    {
        //TODO implement check the database to ensure no collisions
        return false;
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
                priority  +",'"+ location  +"',"+
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
            "AND priority = " + this.priority.getValue() + 
            " AND start_time = '"+ this.startDateTime.toString()+"'" + 
            " AND end_time = '"+ this.endDateTime.toString()+"'" +
            " AND runs_until = '"+ this.runs_until.toString()+"';";
 
        System.out.println("Meeting ID" + getMeetingId);
        SqlHandler sqlh = new SqlHandler();
        ResultSet resultSet = sqlh.runQuery(getMeetingId);
        if(resultSet.next())
        {
            
            this.id = resultSet.getInt("meeting_id");
            result = id;
        }
        return result;
    }
    
    public String RecurrenceHTMLDropDown(){
        return Recurrence.toHTMLDropDown();
        
    }
    public String PriorityHTMLDropDown(boolean admin, boolean lecturer){
        return Priority.toHTML(admin, lecturer);
        
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

    public Priority getPiority() {
        return priority;
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

    public HashSet<Integer> getPeopleId_attendees(Scheduler s) {
        return peopleId_attendees;
    }

    public String getPeopleString() {
        boolean first = true;
        String peps = "";
        for(Integer i:peopleId_attendees)
        {
            if(!first)
            {
                peps +=",";
            }
            peps += i;
            first = false;
        }
        return peps;
    }

    
    
    public HashSet<Integer> getGroupId_attendees(Scheduler s) {
        return groupId_attendees;
    }

    @Override
    public String toString() {
        return "Meeting{" + "title=" + title + ", description=" + description + ", id=" + id + ", hostUserID=" + hostUserID + ", people_attendees=" + people_attendees + ", group_attendees=" + group_attendees + ", length=" + length + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", runs_until=" + runs_until + ", location=" + location + ", recurring=" + recurring + ", repeatEvery=" + repeatEvery + ", piority=" + priority + ", privacy=" + privacy + ", peopleId_attendees=" + peopleId_attendees + ", groupId_attendees=" + groupId_attendees + '}';
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
        hash = 47 * hash + this.priority.getValue();
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
        if (!Objects.equals(this.length,other.length)) {
            System.out.print(this.length + "Length " + other.length);
            return false;
        }
        if (!Objects.equals(this.startDateTime, other.startDateTime)) {
            return false;
        }
        if (!Objects.equals(this.endDateTime, other.endDateTime)) {
            return false;
        }
        if (!Objects.equals(this.runs_until, other.runs_until)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (this.recurring != other.recurring) {
            return false;
        }
        if (this.repeatEvery != other.repeatEvery) {
            return false;
        }
        if (this.priority != other.priority) {
            return false;
        }
        if (this.privacy != other.privacy) {
            System.out.println(this.privacy +"   "+other.privacy);
            return false;
        }
     
        return true;
    }
   /**
    * Turns repeating meeting
    * @param m
    * @param startDate
    * @param endDate
    * @return 
    */
    public static ArrayList<Meeting> expandMeeting(Meeting m, LocalDate startDate, LocalDate endDate)
    {
//        System.out.println(m);
        ArrayList<LocalDateTime> dates = m.getRepeatEvery()
                    .findDatesInRange(m.getStartDateTime(), m.getEndDateTime(),
                                    m.getRuns_until(), startDate, endDate);
        ArrayList<Meeting> meetings = new ArrayList<>();
        for(LocalDateTime date: dates)
        {
            Meeting other = new Meeting(m);
            other.startDateTime = date;
            other.endDateTime = other.startDateTime.plus(m.getLength());
            
            meetings.add(other);
        }
        return meetings;
    }
    
    public static boolean insertMeeting(Meeting meeting, String dateString)
    {
        String[] dates = dateString.split(",");
        Meeting[] meetings = new Meeting[dates.length];
       // boolean[] success = new boolean[dates.length];
        String failed = "";
        int i = 0;
        for(String date: dates)
        {
            meetings[i] = new Meeting(meeting);
            System.out.println("meeting " +i+"  is Null?" + (meetings[i] == null) +" ");
            if(!meetings[i].setTime(date))
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
        for(Meeting m: meetings)
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

        public InsertMeeting(Meeting meeting)
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
                System.out.println(id + ":ID");
                String personAttending = "INSERT INTO `SchedulerDatabase`.`Is_Attending` VALUES ";
                HashSet<Integer> people = meeting.peopleId_attendees;
                int length = people.size();
                for(Integer person:people)
                {
                    personAttending += "(" + id + ", " +  person + ")" ;
                    --length;
                    personAttending += length == 0?";":",";  
                }
                System.out.println("People Attending" + personAttending);
                String groupAttending = "INSERT INTO `SchedulerDatabase`.`Group_Is_Attending` VALUES ";
                HashSet<Integer> groups = meeting.groupId_attendees;
                if(groups != null && groups.size()>0)
                {
                    length = groups.size();
                    for(Integer group : groups)
                    {
                        groupAttending += "(" + id + ", " +  group + ")" ;
                        --length;
                        groupAttending += length == 0?";":",";  
                    }
                    this.runStatement(groupAttending);
                }
                this.runStatement(personAttending);
                
                
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
    
    public String toHTML()
    {
        String html ="<section><fieldset><h1>" + this.getTitle();
        html +="</h1><div><p>Description: " + description;        
        
        html +="</p></div><div><p> People Attendees: </p><ul>";
        SqlHandler sql = new SqlHandler();
        String getNames = "SELECT firstname, secondname FROM user WHERE user_id IN (";
        boolean first = true;
        String names = "";
        for(int p : peopleId_attendees)
        {
            if(!first)
            {
                getNames += ",";
                
            }
            getNames += p;
            first = false;
        }
        getNames += ") ORDER BY secondname;";
       // try {
        
        try {
            ResultSet rs = sql.runQuery(getNames);
            while(rs.next())
            {
                names += "<li>" + rs.getString("firstname")+ " " + rs.getString("secondname") + "</li>";
                
            }
        
        } catch (SQLException ex) {
            names = "<li>failed to load attendies</li>";
        }   
        System.out.print("NAMES" + names);
        html += names;
        if(startDateTime !=null)
        {
            html +="</ul></div><div><p> Start Date: " + startDateTime.toLocalDate();
            html +="</p></div><div><p> Start Time: " + startDateTime.toLocalTime();
        }
        html +="</p></div><div><p> Length:  " + length;
        html +="</p></div><div><p> Location:  " + location ;       
        html +="</p></div><div><p> Repeates:  " + this.repeatEvery;
        html +="</p></div><div><p> Priority:  " + priority + "</p></div></fieldset></section>";
        return html;
    }
    
    public String sendEmails()
    {
        String peps = this.getPeopleString();
        
        SqlHandler sql = new SqlHandler();
        String getEmails = "SELECT email FROM user WHERE user_id IN (";
        getEmails += peps +");";
        ResultSet rs;
        String emails = "";
            
        try {
            rs = sql.runQuery(getEmails);

            boolean first = true;
            while(rs.next())
            {
                if(!first)
                {
                    emails += ",";    
                }
                emails += rs.getString("email");
                first = false;
            }
            System.out.println(emails);
        } catch (SQLException ex) {
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String emailSubject = "You were invited to a Meeting:" +this.getTitle();
        String emailText  = this.getTitle() ;
        

        Email email = new Email();
        email.sendEmail(emailSubject, emailText, emails);
        return emails;
    }
}
