package eu.trentorise.smartcampus.universiadi.model;

public class GPS  {

	public double[] GPS=new double[2];
	public String title;
	public String street;
	

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}