package eu.trentorise.smartcampus.universiadi.Model.ContainerData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.Model.MeetingObj;

public class ContainerMeeting {

	private static ArrayList<MeetingObj> mListaEventi = null;
	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public static ArrayList<MeetingObj> getMeeting() {
		return (mListaEventi == null) ? initList() : mListaEventi;
	}

	private static ArrayList<MeetingObj> initList() {
		mListaEventi = new ArrayList<MeetingObj>();
		for (int i = 0; i < 100; i++) {
			Calendar date = Calendar.getInstance(Locale.getDefault());
			date.set(
					Calendar.DAY_OF_MONTH,
					(i % 2 == 0) ? date.get(Calendar.DAY_OF_MONTH) : date
							.get(Calendar.DAY_OF_MONTH) + i);
			mListaEventi.add(new MeetingObj("Meeting " + i,
					(i % 2 == 0) ? "Ruolo 1" : "Ruolo 2",
					(i % 2 == 0) ? "Ambito 0" : "Ambito 1", LOREM_IPSUM,
					new GeoPoint(45.541465 + i, 11.592808 + i), date
							.getTimeInMillis()));
		}

		return mListaEventi;
	}
}
