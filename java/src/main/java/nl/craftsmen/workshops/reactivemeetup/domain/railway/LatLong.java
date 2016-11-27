package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class LatLong {
	
	private final double EARTH_RADIUS = 6371E3; // meter
	
	private final double latitude;
	
	private final double longitude;

	/**
	 * Creates a new geographic coordinate based on latitude and longitude.
	 * 
	 * @param latitude Latitude in degrees.
	 * @param longitude Longitude in degrees.
	 */
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
	
	/**
	 * Computes the distance to the specified latitude/longitude coordinate relative to the coordinate on which the method is called.
	 * 
	 * @param  other Geographical coordinate for which the distance should be calculated.
	 * @return       Distance in meters.
	 */
	public double distanceTo(LatLong other) {
		double latitude1 = toRadians(this.latitude);
		double latitude2 = toRadians(other.latitude);
		double longitude1 = toRadians(this.longitude);
		double longitude2 = toRadians(other.longitude);
		
		double deltaLatitude = latitude2 - latitude1;
		double deltaLongitude = longitude2 - longitude1;
		
		double a = square(sin(deltaLatitude / 2)) + cos(latitude1) * cos(latitude2) * square(deltaLongitude / 2);
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		
		return EARTH_RADIUS * c;		
	}
		
	private static double toRadians(double degrees) {
		return degrees * PI / 180;
	}
	
	public static double square(double x) {
		return x * x;
	}
}
