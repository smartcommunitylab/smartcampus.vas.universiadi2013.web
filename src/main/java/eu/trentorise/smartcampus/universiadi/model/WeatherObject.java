package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;

public class WeatherObject implements Serializable {

	
	private static final long serialVersionUID = -8003073494371210861L;
	
	//Icona del meteo (Sole, Nuvola, Sole e Nuvola, Pioggia)
	String temperatura;
	GeoPoint gps; //GPS della stazione meteo

}
