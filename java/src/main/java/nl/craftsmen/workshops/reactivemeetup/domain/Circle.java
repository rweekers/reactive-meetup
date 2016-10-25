package nl.craftsmen.workshops.reactivemeetup.domain;

public class Circle implements IShape {

    private final int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }

}
