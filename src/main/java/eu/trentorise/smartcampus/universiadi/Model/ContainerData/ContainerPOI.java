package eu.trentorise.smartcampus.universiadi.Model.ContainerData;

import java.util.ArrayList;

import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.Model.POIObj;

public class ContainerPOI {
	private static ArrayList<POIObj> mListaPOI = null;
	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public static ArrayList<POIObj> getPOI() {
		return (mListaPOI == null) ? initList() : mListaPOI;
	}

	private static ArrayList<POIObj> initList() {
		mListaPOI = new ArrayList<POIObj>();
		for (int i = 0; i < 100; i++) {
			ArrayList<EventObj> mListaEventiNelPosto = ContainerEventi
					.getEventiForLuogo(new GeoPoint(45.541465 + i,
							11.592808 + i));
			mListaPOI.add(new POIObj("Nome " + i, (i % 2 == 0) ? "Categoria 1"
					: "Categoria 2",
					new GeoPoint(45.541465 + i, 11.592808 + i),
					mListaEventiNelPosto));
		}

		return mListaPOI;
	}
}
