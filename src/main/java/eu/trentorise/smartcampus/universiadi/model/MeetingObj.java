package eu.trentorise.smartcampus.universiadi.model;

public class MeetingObj {
	/*
	 * MeetingObj: 1.Nome:String 2.Ruolo:String 3.Ambito:String
	 * 4.Descrizione:String 5.GPS:double[] 6.Data:long
	 */

	private String nome;
	private String ruolo;
	private String ambito;
	private String descrizione;
	private GeoPoint gps;
	private long data;

	public MeetingObj(String nome, String ruolo, String ambito,
			String descrizione, GeoPoint gps, long data) {
		super();
		this.nome = nome;
		this.ruolo = ruolo;
		this.ambito = ambito;
		this.descrizione = descrizione;
		this.gps = gps;
		this.data = data;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public GeoPoint getGps() {
		return gps;
	}

	public void setGps(GeoPoint gps) {
		this.gps = gps;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

}
