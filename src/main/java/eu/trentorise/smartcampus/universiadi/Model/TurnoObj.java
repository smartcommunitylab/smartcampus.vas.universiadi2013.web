package eu.trentorise.smartcampus.universiadi.model;

public class TurnoObj {

	/*
	 * 
	 * TurnoObj: 1.Data:long 2.Luogo:String 4.OraInizio:long 5.OraFine:long
	 */
	
	private long data;
	private String luogo;
	private String categoria;
	private long oraInizio;
	private long oraFine;
	
	public TurnoObj(){
		
		this(0,null,null,0,0);
	}
	
	public TurnoObj(long data, String luogo, String categoria, long oraInizio,
			long oraFine) {
		super();
		this.data = data;
		this.luogo = luogo;
		this.categoria = categoria;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public long getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(long oraInizio) {
		this.oraInizio = oraInizio;
	}

	public long getOraFine() {
		return oraFine;
	}

	public void setOraFine(long oraFine) {
		this.oraFine = oraFine;
	}	
}
