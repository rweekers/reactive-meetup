package nl.craftsmen.workshops.reactivemeetup.domain.cooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake {

    private final String name;
    private final List<Ingredient> ingredients;

    public Cake(String name) {
        this(name, Arrays.asList());
    }
    
    public Cake(String name, List<Ingredient> ingredients) {
    	this.name = name;
    	this.ingredients = new ArrayList<>(ingredients);
    }

    public Cake addIngredient(Ingredient ingredient) {
    	List<Ingredient> newIngredients = new ArrayList<>(ingredients);
    	newIngredients.add(ingredient);
    	
    	return new Cake(name, newIngredients);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(name).append(": ");
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.getName().toLowerCase()).append(" ");
        }
        return sb.toString();
    }
}
