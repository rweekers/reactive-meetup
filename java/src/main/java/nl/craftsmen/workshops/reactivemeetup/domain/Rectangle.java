package nl.craftsmen.workshops.reactivemeetup.domain;

public class Rectangle implements IShape {

    private final String name;
    private final int width;
    private final int height;

    public Rectangle(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculateCircumference() {
        return 2 * width * height;
    }

    @Override
    public String getName() {
        return name;
    }
}
