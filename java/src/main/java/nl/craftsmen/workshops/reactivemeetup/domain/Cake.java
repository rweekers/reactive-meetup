package nl.craftsmen.workshops.reactivemeetup.domain;

import java.util.ArrayList;
import java.util.List;

public class Cake {

    private final String name;
    private final List<Ingredient> ingredients = new ArrayList<>();

    public Cake(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(name).append(": ");
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getName().toLowerCase()).append(" ");
        }
        return sb.toString();
    }
}
