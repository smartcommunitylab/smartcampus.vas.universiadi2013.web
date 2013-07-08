package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;


public class POIObject implements Serializable {

	
	
	private static final long serialVersionUID = -2725604716213011328L;
	private enum POI {GetTicket, InfoPoints, GamePlaces, Hotels, Other}
	private String nome;
	private POI poi;    

	
    public POI getColor(){
       return poi; 
    }
    public void setColor(POI poi){
        this.poi = poi;
    }   
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public GeoPoint getGPS() {
		return GPS;
	}
	public void setGPS(GeoPoint gPS) {
		GPS = gPS;
	}
	private GeoPoint GPS;

	
}
