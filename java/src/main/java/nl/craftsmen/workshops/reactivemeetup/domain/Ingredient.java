package nl.craftsmen.workshops.reactivemeetup.domain;

public class Ingredient {

    private String name;
    private EIngredientType type;

    public Ingredient(String name, EIngredientType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }

    public EIngredientType getType() {
        return type;
    }
}
