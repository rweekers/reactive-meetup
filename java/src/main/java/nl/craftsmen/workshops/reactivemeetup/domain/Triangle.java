package nl.craftsmen.workshops.reactivemeetup.domain;

public class Triangle implements IShape {

    private final int base;
    private final int height;
    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return base * height / 2;
    }

    public double calculateCircumference() {
        return base + height + Math.sqrt(base * base + height * height);
    }
}
