/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.meeting;

/**
 *
 * @author drgex_000
 */
public enum Priority {
    Low(1),
    Low_Mid(2),
    Mid(4),
    Lab(5),
    Lecture(6),
    High(9),
    Exam(10);

    private int value;
    private static final int studentMax = 3;
    private static final int lectureMax = 6;
    private static final int adminMax = 11;
    private Priority(int value)
    {
        this.value = value;
    }
    
    public static String toHTML(boolean admin, boolean lecture)
    {
        int privilage = admin?adminMax:lecture?lectureMax:studentMax; 
        String html = "";
        html += "<select name=\"Piority\">";
        for(Priority p: Priority.values())
        {
            if(p.value > privilage)
            {
                break;
            }
            
            html += "<option value=\"" + p.value + "\">"+ p +"</option>";
            
        }
        html += "</select>";
        return html;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public static Priority getPriorityByValue(int priorityValue)
    {
        for(Priority p: Priority.values())
        {
            if(priorityValue == p.getValue())
            {
                return p;
            }
        }
        return Priority.Low;
    }
}
