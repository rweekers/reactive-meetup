package nl.craftsmen.workshops.reactivemeetup.domain;

public class Circle implements IShape {

    private final String name;
    private final int radius;

    public Circle(String name, int radius) {
        this.name = name;
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getName() {
        return name;
    }

}
