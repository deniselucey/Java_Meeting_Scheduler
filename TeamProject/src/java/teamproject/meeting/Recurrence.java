package teamproject.meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public enum Recurrence
{
	NEVER(Period.ofDays(0)), WEEKLY(Period.ofWeeks(1)), MONTLY(Period.ofMonths(1)), YEARLY(Period.ofYears(1));

	private Period nextIn;

        
        private Recurrence(Period period)
        {
            throw new UnsupportedOperationException();
        }
	/**
	 * 
	 * @param date
	 */
	public LocalDateTime findNextDate(LocalDateTime date)
	{
		// TODO - implement Recurrence.findNextDate
		throw new UnsupportedOperationException();
	}

}