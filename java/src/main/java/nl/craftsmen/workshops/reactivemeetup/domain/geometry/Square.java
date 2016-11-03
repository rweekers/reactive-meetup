package nl.craftsmen.workshops.reactivemeetup.domain.geometry;

public class Square implements IShape {

    private String name;
    private final int width;

    public Square(String name, int width) {
        this.name = name;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return width * width;
    }

    @Override
    public double calculateCircumference() {
        return 4 * width;
    }

    @Override
    public String getName() {
        return name;
    }
}
