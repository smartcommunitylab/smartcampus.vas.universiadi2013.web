package eu.trentorise.smartcampus.universiadi.model;

public class SportObj {
	
	private String nome;
	private int foto;
	private String descrizione;
	
	public SportObj(){
		this(null,-1,null);
	}
	
	public SportObj(String nome, int foto, String descrizione) {
		super();
		this.nome = nome;
		this.foto = foto;
		this.descrizione = descrizione;
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
}