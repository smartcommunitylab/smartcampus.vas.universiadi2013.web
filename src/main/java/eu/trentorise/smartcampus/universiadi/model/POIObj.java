package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;
import java.util.ArrayList;

public class POIObj implements Serializable {

	/*
	 * POIObj: 1.Nome:String 2.Categoria:String 3.GPS:double[]
	 */

	private static final long serialVersionUID = -2725604716213011328L;

	private String nome;
	private String categoria;
	private GeoPoint GPS;
	private ArrayList<EventObj> evento;

	public POIObj(String nome, String categoria, GeoPoint gPS,
			ArrayList<EventObj> evento) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		GPS = gPS;
		this.evento = evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public GeoPoint getGPS() {
		return GPS;
	}

	public void setGPS(GeoPoint gPS) {
		GPS = gPS;
	}

	public ArrayList<EventObj> getEvento() {
		return evento;
	}

	public void setEvento(ArrayList<EventObj> evento) {
		this.evento = evento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
