package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;

public class Evento implements Serializable {

	private class GeoPoint{
		
		private double latGPS;
		private double lngGPS;
		
		public GeoPoint(double latGPS, double lngGPS) {
			super();
			this.latGPS = latGPS;
			this.lngGPS = lngGPS;
		}
		public double getLatGPS() {
			return latGPS;
		}
		public void setLatGPS(double latGPS) {
			this.latGPS = latGPS;
		}
		public double getLngGPS() {
			return lngGPS;
		}
		public void setLngGPS(double lngGPS) {
			this.lngGPS = lngGPS;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6012628151126532583L;
	
	private String nome;
	private Long data;
	private String descrizione;
	private Long id;
	private GeoPoint geo;
	private String indirizzo;
	private int ruolo;
	private String ambito;
	
	public int getRuolo() {
		return ruolo;
	}
	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public GeoPoint getGeo() {
		return geo;
	}
	public void setGeo(double lat, double lng) {
		this.geo = new GeoPoint(lat, lng);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

}
