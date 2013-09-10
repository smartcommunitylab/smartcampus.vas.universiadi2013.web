package eu.trentorise.smartcampus.universiadi.model.ContainerData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;

public class ContainerEventi {

	private static ArrayList<EventObj> mListaEventi = null;
	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public static ArrayList<EventObj> getEventi() {
		return (mListaEventi == null) ? initList() : mListaEventi;
	}

	private static ArrayList<EventObj> initList() {
		mListaEventi = new ArrayList<EventObj>();
		for (int i = 0; i < 100; i++) {
			Calendar date = Calendar.getInstance(Locale.getDefault());
			date.set(
					Calendar.DAY_OF_MONTH,
					(i % 2 == 0) ? date.get(Calendar.DAY_OF_MONTH) : date
							.get(Calendar.DAY_OF_MONTH) + i);
			mListaEventi.add(new EventObj("Evento " + i,
					date.getTimeInMillis(), LOREM_IPSUM, new GeoPoint(
							45.541465 + i, 11.592808 + i), "Sport " + i));
		}

		return mListaEventi;
	}

	public static ArrayList<EventObj> getEventiForLuogo(GeoPoint point) {
		ArrayList<EventObj> mListaEventiNelLuogo = new ArrayList<EventObj>();
		mListaEventi = (mListaEventi == null) ? initList() : mListaEventi;
		for (EventObj event : mListaEventi) {
			if (event.getGps().compareTo(point) == 0)
				mListaEventiNelLuogo.add(event);
		}

		return mListaEventiNelLuogo;
	}
}
