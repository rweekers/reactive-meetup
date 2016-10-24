package nl.craftsmen.workshops.reactivemeetup.domain;

public class Ingredient {

    private String name;
    private String type;

    public Ingredient(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return name;
    }
}
