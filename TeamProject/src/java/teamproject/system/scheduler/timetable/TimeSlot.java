package teamproject.system.scheduler.timetable;

import teamproject.meeting.Meeting;
import java.util.HashSet;
import java.util.Objects;
import javafx.util.Duration;

public class TimeSlot {

    private static final Duration duration = Duration.minutes(15);
    private HashSet<Meeting> meetings;


    public TimeSlot(){
        meetings= new HashSet<>();
    }

    /**
     * Creates a HTML td tag the contains the names and length of each meeting.   
     * @return String HTML td.
     */
    public String toHTML(){
        return this.toHTML(1);
    }
    /**
     * Creates a HTML td tag the contains the names and length of each meeting.   
     * @param row number of rows to span.
     * @return String HTML td with rowspan = row.
     */
    public String toHTML(int row){

        String html = "<td rowspan=\" " +  row +"\" class =";

        switch(meetings.size()) {
            case 0:
                html += "\"\"";
                break;
            case 1:
                html += "\"occupied\"";
                break;
            default:
                html += "\"collision\"";
                break;
        }
        html += ">";

        for (Meeting m1 : meetings) {
            html += m1.getTitle() + " " + m1.getLength() + " ";
        }

        html+="</td>";
        return html;
    }


    /**
     * Add a meeting to the hashSet.
     * @param meeting meeting to add.
     */
    public void add(Meeting meeting){
        meetings.add(meeting);
    }
    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements. 
     */
    public boolean isEmpty(){
        return meetings.isEmpty();
    }
    
    /**
     * 
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.meetings);
        return hash;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }  
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeSlot other = (TimeSlot) obj;
        if (!Objects.equals(this.meetings, other.meetings)) {
            return false;
        }
        return true;
    }

    public static Duration getDuration() {
        return duration;
    }
       
}
