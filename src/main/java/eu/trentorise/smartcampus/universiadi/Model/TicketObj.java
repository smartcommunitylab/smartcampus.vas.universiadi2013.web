package eu.trentorise.smartcampus.universiadi.model;

public class TicketObj {

	/*
	 * TicketObj: 1.GPS:double[] 2.Descrizione:String 3.Categoria:String
	 * 4.Foto(link):String
	 */
	
	private GeoPoint gps;
	private String descrizione;
	private String categoria;
	private String foto;
	
	public TicketObj(GeoPoint gps, String descrizione, String categoria,
			String foto) {
		super();
		this.gps = gps;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.foto = foto;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
