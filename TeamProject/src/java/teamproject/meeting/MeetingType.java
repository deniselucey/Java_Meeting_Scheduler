package teamproject.meeting;

public enum MeetingType
{
	PERSONAL(1),
        LAB(4),
        LECTURE(5),
        EXAM(11), 
        LOW(1), 
        LOWMID(3), 
        MID(5), 
        MIDHIGH(7), 
        HIGH(9), 
        VERYHIGH(11),
        PERFEREDTOBETAKEN(-1);

	private int priority;

	/**
	 * 
	 * @param priority
	 */
	private MeetingType(int priority)
	{
		// TODO - implement MeetingType.MEETING_TYPE
		throw new UnsupportedOperationException();
	}

	public int getPriority()
	{
		return this.priority;
	}

}