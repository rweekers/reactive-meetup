const EARTH_RADIUS = 6371E3;

module.exports = class LatLong {

    /**
     * Creates a new geographic coordinate based on latitude and longitude.
     */
    constructor(latitude, longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    getLatitude() {
		return this.latitude;
	}
	
    getLongitude() {
		return this.longitude;
	}

    /**
	 * Computes the distance to the specified latitude/longitude coordinate relative to the coordinate on which 
     * the method is called.
	 */
	 distanceTo(other) {
		const latitude1 = this.toRadians(this.latitude);
		const latitude2 = this.toRadians(other.latitude);
		const longitude1 = this.toRadians(this.longitude);
		const longitude2 = this.toRadians(other.longitude);
		
		const deltaLatitude = latitude2 - latitude1;
		const deltaLongitude = longitude2 - longitude1;
		
		const a = this.square(Math.sin(deltaLatitude / 2)) + Math.cos(latitude1) * Math.cos(latitude2) * this.square(deltaLongitude / 2);
		const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return EARTH_RADIUS * c;		
	}
	
	/**
	 * Computes the latitude/longitude coordinate that lies at the specified offset of the linear interpolation between the given coordinate
	 * and the coordinate on which this method is called. The offset is expected to be normalised, where an offset of 0.0 is equal to the
	 * coordinate in which this method is called and where an offset of 1.0 is equal to the other coordinate.
	 */
	interpolate(other, offset) {
		return new LatLong(
			this.latitude * (1 - offset) + other.latitude * offset,
			this.longitude * (1 - offset) + other.longitude * offset
		);
	}
	
	toString() {
		return "(" + this.latitude + ", " + this.longitude + ")";
	}

	square(x) {
		return x * x;
	}

	toRadians(degrees) {
		return degrees * Math.PI / 180;
	}
}
