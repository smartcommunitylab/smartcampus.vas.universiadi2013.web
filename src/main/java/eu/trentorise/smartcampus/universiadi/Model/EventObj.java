package eu.trentorise.smartcampus.universiadi.Model;

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

	private String indirizzo = null;
	private ArrayList<AtletObj> listaAtleti = null;

	public EventObj(String nome, long data, String descrizione, GeoPoint gps,
			String tipoSport) {
		super();
		this.nome = nome;
		this.data = data;
		this.descrizione = descrizione;
		this.gps = gps;
		this.tipoSport = tipoSport;

		indirizzo = reverseGeoCoding();

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

	public String getIndirizzo() {
		return indirizzo;
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

	private String reverseGeoCoding() {
		try {

			URL url = new URL(
					"http://maps.googleapis.com/maps/api/geocode/json?latlng="
							+ gps.getLatGPS() + "," + gps.getLngGPS()
							+ "&sensor=true");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String line = "";
			String output = "";
			System.out.println("Output from Server .... \n");
			while ((line = br.readLine()) != null) {
				output = output + line;
			}

			conn.disconnect();
			indirizzo = new JSONObject(output).getJSONArray("results")
					.getJSONObject(0).getString("formatted_address");
			return indirizzo;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
