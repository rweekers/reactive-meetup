package nl.craftsmen.workshops.reactivemeetup.domain;

public class Triangle implements IShape {

    private final String name;
    private final int base;
    private final int height;

    public Triangle(String name, int base, int height) {
        this.name = name;
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return base * height / 2;
    }

    @Override
    public double calculateCircumference() {
        return base + height + Math.sqrt(base * base + height * height);
    }

    @Override
    public String getName() { return name; }
}
