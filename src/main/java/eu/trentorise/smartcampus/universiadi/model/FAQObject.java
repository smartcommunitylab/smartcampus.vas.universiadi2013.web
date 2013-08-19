package eu.trentorise.smartcampus.universiadi.model;

import java.util.List;

public class FAQObject {
	
	private int id;
	private String domanda;
	private String risposta;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRisposta() {
		return risposta;
	}
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	public String getDomanda() {
		return domanda;
	}
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}
	

}
