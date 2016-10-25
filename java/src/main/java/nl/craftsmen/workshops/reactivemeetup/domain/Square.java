package nl.craftsmen.workshops.reactivemeetup.domain;

public class Square implements  IShape {

    private final int width;

    public Square(int width) {
        this.width = width;
    }

    public double calculateArea() {
        return width * width;
    }

    public double calculateCircumference() {
        return 4 * width;
    }
}
