package eu.trentorise.smartcampus.universiadi.model;

public class AtletObj {

	/*
	 * AtletObj: 1.Nome:String 2.Cognome:String 3.Nazionalitˆ:String
	 * 4.Disciplina:String 5.Foto:byte[]
	 */
	
	private String nome;
	private String cognome;
	private String nazionalita;
	private String disciplina;
	private byte[] foto;
	
	public AtletObj(){
		
		this(null,null,null,null,new byte[1]);
	}
	public AtletObj(String nome, String cognome, String nazionalita,
			String disciplina, byte[] foto) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.disciplina = disciplina;
		this.foto = foto;
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

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
}
