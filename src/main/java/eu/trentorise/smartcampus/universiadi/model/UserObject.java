package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;

public class UserObject  {

	
	
	private String nome;
	private String cognome;
	private String ambito; //String che rappresenta l'ambito (es. Mensa1)
	String user;
	String password;
	String token;
	private int ruolo;  //int che rappresenta il ruolo (es. 2)
	private long badgeCode;
	private String numeroTelefonico;
	//private List<UserObject> listaSuperiori;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public int getRuolo() {
		return ruolo;
	}
	public void setRuolo(int ruolo) {
		this.ruolo = ruolo;
	}
	public long getBadgeCode() {
		return badgeCode;
	}
	public void setBadgeCode(long badgeCode) {
		this.badgeCode = badgeCode;
	}
	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}
	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

}
