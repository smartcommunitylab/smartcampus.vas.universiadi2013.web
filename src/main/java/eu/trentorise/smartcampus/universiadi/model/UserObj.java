package eu.trentorise.smartcampus.universiadi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class UserObj implements Serializable {

	/*
	 * UserObj: 1.Nome:String 2.Cognome:String 3.Ruolo:String 4.Ambito:String
	 * 5.Foto:byte[] 6.Numero di telefono:String
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -634649924719428244L;
	private String nome;
	private String cognome;
	private String ambito;
	private String ruolo;
	private byte[] foto;
	private String user;
	private String password;
	private String numeroTelefonico;

	public UserObj() {

		this(null, null, null, null, new byte[1], null, null, null);
	}

	private String token = null;
	private ArrayList<TurnoObj> listaTurni = new ArrayList<TurnoObj>();

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserObj(String nome, String cognome, String ambito, String ruolo,
			byte[] foto, String user, String password, String numeroTelefonico) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.ambito = ambito;
		this.ruolo = ruolo;
		this.foto = foto;
		this.user = user;
		this.password = password;
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public ArrayList<TurnoObj> getListaTurni() {
		return (listaTurni.size() == 0) ? compileListaTurni() : listaTurni;
	}

	private ArrayList<TurnoObj> compileListaTurni() {
		listaTurni = new ArrayList<TurnoObj>();
		for (int i = 0; i < 3; i++) {
			Calendar date = Calendar.getInstance(Locale.getDefault());
			date.set(
					Calendar.DAY_OF_MONTH,
					(i >= 1) ? date.get(Calendar.DAY_OF_MONTH) : date
							.get(Calendar.DAY_OF_MONTH) + 1);
			date.set(Calendar.HOUR_OF_DAY, 8);
			date.set(Calendar.MINUTE, 0);
			listaTurni.add(new TurnoObj(date.getTimeInMillis(),
					(i % 2 == 0) ? "Luogo 1" : "Luogo 2", getAmbito(), date
							.getTimeInMillis(), date.getTimeInMillis()
							+ (3600 * 1000 * 3)));
		}

		return listaTurni;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
