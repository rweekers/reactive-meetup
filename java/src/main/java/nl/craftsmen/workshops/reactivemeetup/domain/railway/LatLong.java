package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class LatLong {
	
	private final double latitude;
	
	private final double longitude;

	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double distanceTo(LatLong other) {
		throw new UnsupportedOperationException(); // TODO implement this.
	}
}
