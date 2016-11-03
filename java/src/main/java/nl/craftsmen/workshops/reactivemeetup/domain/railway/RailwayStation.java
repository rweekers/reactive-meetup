package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class RailwayStation {

    private ERailwayStation id;
    private String name;
    private double longitude;
    private double latitude;

    public RailwayStation(ERailwayStation id, String name, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public ERailwayStation getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
