package teamproject.meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public enum Recurrence
{
	NEVER(Period.ofYears(1000)),
        WEEKLY(Period.ofWeeks(1)),
        MONTHLY(Period.ofWeeks(4)),
        YEARLY(Period.ofYears(1));

	private  Period  period;

        public Period getPeriod()
        {
            return Period.ZERO.plus(period);
        }

        private Recurrence(Period period)
        {
            this.period = period;
        }
	/**
	 * 
	 * @param date
         * @return 
	 */
	public LocalDateTime findNextDate(LocalDateTime date)
	{
		return date.plus(period);
	}
        
        /**
         * Find all date and time of repeating dates 
         * @param meetingStartTime
         * @param meetingEndTime
         * @param meetingRunsUntil
         * @param start
         * @param end
         * @return 
         */
        public ArrayList<LocalDateTime> findDatesInRange(LocalDateTime meetingStartTime, LocalDateTime meetingEndTime, LocalDate meetingRunsUntil, LocalDate start, LocalDate end)
        {
            LocalDateTime tempStart = meetingStartTime;
            LocalDateTime tempEnd = meetingEndTime;
            
            ArrayList<LocalDateTime> result = new ArrayList<>();
            while(tempStart.isBefore(end.atStartOfDay()))
            {
                if(tempStart.isAfter(start.atStartOfDay()) || tempEnd.isAfter(start.atStartOfDay()))
                {
                   result.add(tempStart);
                }
                tempStart = tempStart.plus(this.getPeriod());
                tempEnd = tempEnd.plus(this.getPeriod());
            }
            return result;
        }
        
        /**
         * 
         * @param time period of time that you want to get a recurrence of.
         * @return 
         */
        public static Recurrence findByPeriod(Period time)
        {
            //return Arrays.stream(Recurrence.values()).parallel().filter(rec -> rec.period.equals(time)).findFirst().orElse(null);
            for(Recurrence rec : Recurrence.values())
            {
               if( rec.period.equals(time))
               {
                   return rec;
               }
            }
            return Recurrence.NEVER;
        }
        
        public static String toHTMLDropDown()
        {
            String html = "";
            html += "<select name=\"" + Recurrence.class.getName() + "\">";
            for(Recurrence r:Recurrence.values())
            {
                  html += "<option value=\"" + r.getPeriod() + "\">"+ r.name() +"</option>";
            }
            html += "</select>";
            return html;
        }
}