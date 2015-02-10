package teamproject.meeting;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;

public enum Recurrence
{
	NEVER(Period.ofDays(0)),
        WEEKLY(Period.ofWeeks(1)),
        MONTLY(Period.ofMonths(1)),
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
         * 
         * @param time period of time that you want to get a recurrence of.
         * @return 
         */
        public static Recurrence findByPeriod(Period time)
        {
            return Arrays.stream(Recurrence.values()).parallel().filter(rec -> rec.period.equals(time)).findFirst().orElse(null);
        }
}