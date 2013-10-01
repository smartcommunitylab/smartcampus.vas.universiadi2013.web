package eu.trentorise.smartcampus.universiadi.model.containerData;

import java.util.ArrayList;

import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObj;

public class ContainerPOI {
	private static ArrayList<POIObj> mListaPOI = null;
	
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
