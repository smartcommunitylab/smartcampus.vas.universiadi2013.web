package eu.trentorise.smartcampus.universiadi.Model;

import java.io.Serializable;

public class TurnoObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2963916580809389760L;
	/*
	 * 
	 * TurnoObj: 1.Data:long 2.Luogo:String 4.OraInizio:long 5.OraFine:long
	 */
	
	private long data;
	private String luogo;
	private long oraInizio;
	private long oraFine;
	private UserObj volontario;
	
	public TurnoObj(long data, String luogo, long oraInizio, long oraFine,
			UserObj volontario) {
		super();
		this.data = data;
		this.luogo = luogo;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.volontario = volontario;
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

	public UserObj getVolontario() {
		return volontario;
	}

	public void setVolontario(UserObj volontario) {
		this.volontario = volontario;
	}	
}
