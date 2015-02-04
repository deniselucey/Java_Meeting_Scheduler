package teamproject.user;

import java.util.ArrayList;
import teamproject.system.scheduler.timetable.Timetable;

public class User {

	private Person self;
	private ArrayList<Timetable> timetable;
	private Privilege privilege;
	private UserSetting setting;
	private ArrayList<Person> friends;
	private boolean lecture;

	public boolean login()
	{
		// TODO - implement User.login
		throw new UnsupportedOperationException();
	}

	public boolean logout()
	{
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param friend
	 */
	public boolean sendFriendInvite(Person friend)
	{
		// TODO - implement User.sendFriendInvite
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param friend
	 */
	public boolean cancleFriendInvite(Person friend)
	{
		// TODO - implement User.cancleFriendInvite
		throw new UnsupportedOperationException();
	}

	public boolean removeFriend()
	{
		// TODO - implement User.removeFriend
		throw new UnsupportedOperationException();
	}

}