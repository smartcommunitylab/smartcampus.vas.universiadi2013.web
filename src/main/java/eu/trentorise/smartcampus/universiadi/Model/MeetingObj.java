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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MeetingObj implements Serializable{
	/*
	 * MeetingObj: 1.Nome:String 2.Ruolo:String 3.Ambito:String
	 * 4.Descrizione:String 5.GPS:double[] 6.Data:long
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045135562089833056L;
	private String nome;
	private String ruolo;
	private String ambito;
	private String descrizione;
	private GeoPoint gps;
	private long data;

	private String indirizzo = null;
	private ArrayList<ElementDescRoute> routeTestuale = null;

	public MeetingObj(String nome, String ruolo, String ambito,
			String descrizione, GeoPoint gps, long data) {
		super();
		this.nome = nome;
		this.ruolo = ruolo;
		this.ambito = ambito;
		this.descrizione = descrizione;
		this.gps = gps;
		this.data = data;

		indirizzo = reverseGeoCoding();
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public ArrayList<ElementDescRoute> getRouteTestuale(double[] source) {
		return (routeTestuale == null) ? parseGoogleDescRoute(source,
				new double[] { gps.getLatGPS(), gps.getLngGPS() })
				: routeTestuale;
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

	private ArrayList<ElementDescRoute> parseGoogleDescRoute(
			double[] gpsSource, double[] gpsDest) {
		URL url;
		try {
			String srcGPS = gpsSource[0] + "," + gpsSource[1];
			String destGPS = gpsDest[0] + "," + gpsDest[1];
			String path = "http://maps.googleapis.com/maps/api/directions/json?origin="
					+ srcGPS
					+ "&destination="
					+ destGPS
					+ "&sensor=false&language=it";
			url = new URL(path);
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

			JSONObject object = new JSONObject(output);

			ArrayList<ElementDescRoute> result = new ArrayList<ElementDescRoute>();
			// Get routes
			JSONArray legs;
			legs = object.getJSONArray("routes").getJSONObject(0)
					.getJSONArray("legs");
			JSONObject leg = legs.getJSONObject(0);
			result.add(new ElementDescRoute(leg.getJSONObject("distance")
					.getString("text"), leg.getJSONObject("duration")
					.getString("text"), leg.getString("end_address"), "0"));

			JSONArray steps = leg.getJSONArray("steps");

			for (int j = 0; j < steps.length(); j++) {
				String img = "0";
				JSONObject step = steps.getJSONObject(j);
				try {
					img = step.getString("maneuver");
				} catch (JSONException e) {
				}

				String desc = step.getString("html_instructions").replaceAll(
						"\\<.*?>\n", "");
				result.add(new ElementDescRoute(step.getJSONObject("distance")
						.getString("text"), step.getJSONObject("duration")
						.getString("text"), desc, img));
			}

			routeTestuale = result;
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

}
