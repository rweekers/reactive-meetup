package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class Train {

    private String name;
    private double longitude;
    private double latitude;

    public Train(String name, double latitude, double longitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
