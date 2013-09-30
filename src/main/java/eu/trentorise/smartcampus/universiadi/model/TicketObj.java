package eu.trentorise.smartcampus.universiadi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ticketobj")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketObj {

	/*
	 * TicketObj: 1.GPS:double[] 2.Descrizione:String 3.Categoria:String
	 * 4.Foto(link):String
	 */

	//{"GPS": [0,0], "Descrizione": "test", "Ambito": "", "Foto": "", "Indirizzo": "via test","Telefono": "00000"}
	
	private GeoPoint gps;
	private String descrizione;
	private String ambito;
	private String foto;
	private String indirizzo;
	private String telefono;
	private String status;
	private String _id;

	public TicketObj(GeoPoint gps, String descrizione, String ambito,String indirizzo,String telefono,
			String foto) {
		super();
		this.gps = gps;
		this.descrizione = descrizione;
		this.ambito = ambito;
		this.foto = foto;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
	}

	public GeoPoint getGps() {
		return gps;
	}

	public void setGps(GeoPoint gps) {
		this.gps = gps;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	public String toJson(){
		return "{\"gps\": "+getGps().toJson()+",\"descrizione\":\""+getDescrizione()+"\",\"ambito\": \""+getAmbito()+"\",\"foto\": \""+getFoto()+"\",\"indirizzo\": \""+getIndirizzo()+"\",\"telefono\": \""+getTelefono()+"\"}";
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
