package eu.trentorise.smartcampus.universiadi.Model.ContainerData;

import java.util.ArrayList;

import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.Model.POIObj;
import eu.trentorise.smartcampus.universiadi.Model.UserObj;

public class ContainerUtenti {
	private static ArrayList<UserObj> mListaUtenti = null;
	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public static ArrayList<UserObj> getUtenti() {
		return (mListaUtenti == null) ? initList() : mListaUtenti;
	}

	public static ArrayList<UserObj> getUtentiForAmbitoAndRuolo(String ambito,
			String ruolo) {
		ArrayList<UserObj> mListaUtenti = getUtenti();
		ArrayList<UserObj> mListaUtentiPerAmbito = new ArrayList<UserObj>();

		for (UserObj utente : mListaUtenti) {
			if (!ambito.equalsIgnoreCase("") && !ruolo.equalsIgnoreCase("")) {
				if (utente.getAmbito().equalsIgnoreCase(ambito)
						&& utente.getRuolo().equalsIgnoreCase(ruolo))
					mListaUtentiPerAmbito.add(utente);
			} else if (!ambito.equalsIgnoreCase("")) {
				if (utente.getAmbito().equalsIgnoreCase(ambito))
					mListaUtentiPerAmbito.add(utente);
			} else if (!ruolo.equalsIgnoreCase("")) {
				if (utente.getRuolo().equalsIgnoreCase(ruolo))
					mListaUtentiPerAmbito.add(utente);
			} else {
				mListaUtentiPerAmbito.add(utente);
			}

		}

		return mListaUtentiPerAmbito;
	}

	public static ArrayList<UserObj> getSuperiori(UserObj utente) {
		ArrayList<UserObj> mListaUtenti = getUtenti();
		ArrayList<UserObj> mListaSuperiori = new ArrayList<UserObj>();

		for (UserObj user : mListaUtenti)
			if (utente.getAmbito().equalsIgnoreCase(utente.getAmbito())
					&& Integer.parseInt(user.getRuolo()) < Integer
							.parseInt(utente.getRuolo()))
				mListaSuperiori.add(utente);

		return mListaSuperiori;
	}

	private static ArrayList<UserObj> initList() {
		mListaUtenti = new ArrayList<UserObj>();
		for (int i = 0; i < 100; i++) {
			mListaUtenti.add(new UserObj("Nome " + i, "Cognome " + 1,
					(i % 2) == 0 ? "Ambito 1" : "Ambito 2", Integer
							.toString(i % 5), new byte[1], "test", "test",
					"0444000" + i));
		}

		return mListaUtenti;
	}
}
