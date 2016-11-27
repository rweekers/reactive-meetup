package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public enum RailwayStation {

    AMS("AMS", "Amsterdam Centraal", new LatLong(52.3791283, 4.8980833)),
    DB("DB",   "", new LatLong(51.6905476, 5.2913696)),
    DH("DH",   "Den Haag", new LatLong(52.0809271, 4.3222312)),
    AMR("AMR", "Amersfoort", new LatLong(52.1530195, 5.3711025)),
    UTR("UTR", "Utrecht Centraal", new LatLong(52.0893224, 5.1079804));

	private final String id;
	
	private final String name;
	
	private final LatLong location;
	
	RailwayStation(String id, String name, LatLong location) {
		this.id = id;
		this.name= name;
		this.location = location;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LatLong getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return name + " (" + id + ")";
	}

}
