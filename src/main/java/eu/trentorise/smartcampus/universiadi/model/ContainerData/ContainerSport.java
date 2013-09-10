package eu.trentorise.smartcampus.universiadi.model.ContainerData;

import java.util.ArrayList;

import eu.trentorise.smartcampus.universiadi.model.SportObj;

public class ContainerSport {
	private static ArrayList<SportObj> mListaSport = null;

	public static ArrayList<SportObj> getSport() {
		return (mListaSport == null) ? initList() : mListaSport;
	}

	private static ArrayList<SportObj> initList() {
		mListaSport = new ArrayList<SportObj>();

		mListaSport.add(new SportObj("Sport 0", 0, "Descrizione Alpine"));
		mListaSport.add(new SportObj("Sport 1", 1, "Descrizione Cross"));
		mListaSport.add(new SportObj("Sport 2", 2, "Descrizione Curling"));
		mListaSport.add(new SportObj("Sport 3", 3, "Descrizione Figure"));
		mListaSport.add(new SportObj("Sport 4", 4, "Descrizione Freestyle"));
		mListaSport.add(new SportObj("Sport 5", 5, "Descrizione Ice"));
		mListaSport.add(new SportObj("Sport 6", 6, "Descrizione Nordic"));
		mListaSport.add(new SportObj("Sport 7", 7, "Descrizione Shorttracking"));
		mListaSport.add(new SportObj("Sport 8", 8, "Descrizione Ski Jumping"));
		mListaSport.add(new SportObj("Sport 9", 9, "Descrizione Snowboarding"));
		mListaSport.add(new SportObj("Sport 10", 10, "Descrizione SpeedSkating"));
		mListaSport.add(new SportObj("Sport 11", 11, "Descrizione Biathlon"));

		return mListaSport;
	}
}
