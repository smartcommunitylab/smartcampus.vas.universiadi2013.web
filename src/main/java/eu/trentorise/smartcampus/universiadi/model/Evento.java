package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;

public class Evento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6012628151126532583L;
	
	private String nome;
	private String data;
	private String descrizione;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	

	

}
