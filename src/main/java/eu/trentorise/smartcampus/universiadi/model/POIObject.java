package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;


public class POIObject implements Serializable {

	
	
	private static final long serialVersionUID = -2725604716213011328L;
	
	private String nome;
	private String poitype;
	private GeoPoint GPS;

	
	
    public String getPOIType(){
       return poitype; 
    }
    public void setPOIType(String poi){
        this.poitype = poi;
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

	
}
