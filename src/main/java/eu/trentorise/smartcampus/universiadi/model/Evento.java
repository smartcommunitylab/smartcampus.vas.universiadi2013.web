package eu.trentorise.smartcampus.universiadi.model;

import eu.trentorise.smartcampus.presentation.data.BasicObject;

public class Evento extends BasicObject  {

	
	private static final long serialVersionUID = -6012628151126532583L;
	
	private String nome;
	private Long data;
	private String descrizione;
	private GeoPoint geo;
	private String indirizzo;
	private String tipoSport;
	private int ruolo;
	private String ambito;
	
	public String getTipoSport() {
		return tipoSport;
	}
	public void setTipoSport(String tipoSport) {
		this.tipoSport = tipoSport;
	}
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
