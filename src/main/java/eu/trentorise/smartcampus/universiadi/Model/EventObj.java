package eu.trentorise.smartcampus.universiadi.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

public class EventObj implements Serializable {
	/**
	 * 1.Nome:String 2.Data:long 3.Descrizione:String 4.TipoSport:String
	 * 5.GPS:double[] 6.RouteTestuale:String 7.Altri dati (se presenti)
	 */
	private static final long serialVersionUID = -6012628151126532583L;

	private String nome;
	private long data;
	private String descrizione;
	private GeoPoint gps;
	private String tipoSport;

	
	
	private ArrayList<AtletObj> listaAtleti = null;
	
	public EventObj(){
		
		this(null, 0, null, null, null);
		
	}
	public EventObj(String nome, long data, String descrizione, GeoPoint gps,
			String tipoSport) {
		super();
		this.nome = nome;
		this.data = data;
		this.descrizione = descrizione;
		this.gps = gps;
		this.tipoSport = tipoSport;

		

		listaAtleti = new ArrayList<AtletObj>();
		for (int i = 0; i < 6; i++)
			listaAtleti.add(new AtletObj("Nome " + i, "Cognome " + i, "Italia",
					(i % 2 == 0) ? "Scii" : "Snowboard", new byte[1]));
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

	public GeoPoint getGps() {
		return gps;
	}

	public void setGps(GeoPoint gps) {
		this.gps = gps;
	}


	public String getTipoSport() {
		return tipoSport;
	}

	public void setTipoSport(String tipoSport) {
		this.tipoSport = tipoSport;
	}

	
	public ArrayList<AtletObj> getListaAtleti() {
		return listaAtleti;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
