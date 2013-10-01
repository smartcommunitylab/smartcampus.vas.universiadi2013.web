package eu.trentorise.smartcampus.universiadi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@XmlRootElement(name = "ticketobj")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketObj {

	private double GPS[];
	private String Descrizione;
	private String Ambito;
	private String Foto;
	private String Indirizzo;
	private String Telefono;
	private String Status;
	private String _id;

	public TicketObj(double[] gps, String descrizione, String ambito,
			String indirizzo, String telefono, String foto) {
		super();
		this.GPS = gps;
		this.Descrizione = descrizione;
		this.Ambito = ambito;
		this.Foto = foto;
		this.Indirizzo = indirizzo;
		this.Telefono = telefono;
	}

	public TicketObj() {
		// TODO Auto-generated constructor stub
	}

	public String toJson() {
		return "{\"GPS\": [" + getGPS()[0] +","+getGPS()[1]+ "],\"Descrizione\":\""
				+ getDescrizione() + "\",\"Ambito\": \"" + getAmbito()
				+ "\",\"Foto\": \"" + getFoto() + "\",\"Indirizzo\": \""
				+ getIndirizzo() + "\",\"Telefono\": \"" + getTelefono()
				+ "\"}";

	}

	public TicketObj valueOf(String response) {
		try {
			JSONObject json = new JSONObject(response);
			TicketObj data = new TicketObj();
			JSONArray x = json.getJSONArray("GPS");

			data.setGPS(new double[] { x.getDouble(0), x.getDouble(1) });
			data.Descrizione = json.getString("Descrizione");
			data.Ambito = json.getString("Ambito");
			data.Foto = json.getString("Foto");
			data.Indirizzo = json.getString("Indirizzo");
			data.Telefono = json.getString("Telefono");
			data.Status = json.getString("status");
			data._id = json.getString("_id");

			return data;
		} catch (JSONException e) {
			return null;
		}

	}

	public double[] getGPS() {
		return GPS;
	}

	public void setGPS(double[] gPS) {
		GPS = gPS;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	public String getAmbito() {
		return Ambito;
	}

	public void setAmbito(String ambito) {
		Ambito = ambito;
	}

	public String getFoto() {
		return Foto;
	}

	public void setFoto(String foto) {
		Foto = foto;
	}

	public String getIndirizzo() {
		return Indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
