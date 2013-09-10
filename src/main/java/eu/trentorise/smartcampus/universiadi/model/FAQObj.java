package eu.trentorise.smartcampus.universiadi.model;

public class FAQObj {

	private int id;
	private String domanda;
	private String risposta;
	private int totalTag;
	private int usefulTag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public int getTotalTag() {
		return totalTag;
	}

	public void setTotalTag(int totalTag) {
		this.totalTag = totalTag;
	}

	public int getUsefulTag() {
		return usefulTag;
	}

	public void setUsefulTag(int usefulTag) {
		this.usefulTag = usefulTag;
	}

}
