package eu.trentorise.smartcampus.universiadi.model;

public class GeoPoint implements Comparable<GeoPoint> {

	public double latGPS;
	public double lngGPS;

	public GeoPoint(){
		this(0,0);
	}
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

	@Override
	public int compareTo(GeoPoint o) {
		// TODO Auto-generated method stub
		if (o.getLatGPS() == latGPS && o.getLngGPS() == lngGPS)
			return 0;
		return -1;
	}
}