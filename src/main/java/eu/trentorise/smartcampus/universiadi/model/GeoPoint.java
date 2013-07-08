package eu.trentorise.smartcampus.universiadi.model;

public class GeoPoint{
	
	private double latGPS;
	private double lngGPS;
	
	public GeoPoint(double latGPS, double lngGPS) {
		super();
		this.latGPS = latGPS;
		this.lngGPS = lngGPS;
	}
	public double getLatGPS() {
		return latGPS;
	}
	public void setLatGPS(double latGPS) {
		this.latGPS = latGPS;
	}
	public double getLngGPS() {
		return lngGPS;
	}
	public void setLngGPS(double lngGPS) {
		this.lngGPS = lngGPS;
	}
}