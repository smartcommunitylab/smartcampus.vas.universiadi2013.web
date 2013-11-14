package eu.trentorise.smartcampus.universiadi.model;

public class GPS  {

	public double[] GPS;
	

	public GPS() {
		this(0, 0);
	}

	public GPS(double latGPS, double lngGPS) {
		super();
		this.GPS[0] = latGPS;
		this.GPS[1] = lngGPS;
	}


	
	public double[] getArray(){		
		return GPS;
	}
}