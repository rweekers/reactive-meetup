package nl.craftsmen.workshops.reactivemeetup.domain.cooking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake {

	private final String name;
	private final List<Ingredient> ingredients;
	private final boolean baked;

	public Cake(String name) {
		this(name, Arrays.asList(), false);
	}

	public Cake(String name, List<Ingredient> ingredients, boolean baked) {
		this.name = name;
		this.ingredients = new ArrayList<>(ingredients);
		this.baked = baked;
	}

	public Cake addIngredient(Ingredient ingredient) {
		if (baked) {
			throw new IllegalStateException("Nope. You cannot add any ingredients after the cake has been baked.");
		}
		
		List<Ingredient> newIngredients = new ArrayList<>(ingredients);
		newIngredients.add(ingredient);

		return new Cake(name, newIngredients, baked);
	}
	
	public Cake bake() {
		if (ingredients.isEmpty()) {
			throw new IllegalStateException("Trying to serve \"gebakken lucht\"? Add some ingredients before baking the cake!");
		}
		if (baked) {
			throw new IllegalStateException("Oops, you've baked the cake again, now it is burned!");
		}
		return new Cake(name, ingredients, true);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(name).append(':');
		for (Ingredient ingredient : ingredients) {
			sb.append(' ').append(ingredient.getName().toLowerCase());
		}
		if (baked) {
			sb.append(" - freshly baked, om nom nom!");
		} else {
			sb.append(" - still uncooked :(");
		}
		return sb.toString();
	}
}
