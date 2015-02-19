package teamproject.meeting;

public enum MeetingType
{
	PERSONAL((byte)1),
        LAB((byte)4),
        LECTURE((byte)5),
        EXAM((byte)11), 
        LOW((byte)1), 
        LOWMID((byte)3), 
        MID((byte)5), 
        MIDHIGH((byte)7), 
        HIGH((byte)9), 
        VERYHIGH((byte)11),
        PERFEREDTOBETAKEN((byte)-1);

	private byte priority;

	/**
	 * 
	 * @param priority
	 */
	private MeetingType(byte priority)
	{
            this.priority = priority;
	}

	public byte getPriority()
	{
		return this.priority;
	}

}