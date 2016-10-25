package nl.craftsmen.workshops.reactivemeetup.domain;

public class Rectangle implements IShape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double calculateArea() {
        return width * height;
    }

    public double calculateCircumference() {
        return 2 * width * height;
    }
}
