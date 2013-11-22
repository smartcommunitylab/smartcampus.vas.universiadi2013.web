package eu.trentorise.smartcampus.universiadi.model;

public class SportObj {

	private String nome;
	private int foto;
	private String descrizione;
	private GPS[] geolocations;
	private String atleti;
	private String specialita;
	private String nomeEn;
	

	public SportObj() {
		this(null, -1, null, null, null);
	}

	public SportObj(String nome, int foto, String descrizione,String specialita,String atleti) {
		super();
		this.nome = nome;
		this.foto = foto;
		this.descrizione = descrizione;
		this.atleti=atleti;
		this.specialita=specialita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getFoto() {
		return foto;
	}

	public void setFoto(int foto) {
		this.foto = foto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public GPS[] getGeolocations() {
		return geolocations;
	}

	public void setGeolocations(GPS[] geolocations) {
		this.geolocations = geolocations;
	}

	public String getAtleti() {
		return atleti;
	}

	public void setAtleti(String atleti) {
		this.atleti = atleti;
	}

	public String getSpecialita() {
		return specialita;
	}

	public void setSpecialita(String specialita) {
		this.specialita = specialita;
	}

	public String getNomeEn() {
		return nomeEn;
	}

	public void setNomeEn(String nomeEn) {
		this.nomeEn = nomeEn;
	}

	

	
}