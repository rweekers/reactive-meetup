package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.cooking.Cake;
import nl.craftsmen.workshops.reactivemeetup.domain.cooking.EIngredientType;
import nl.craftsmen.workshops.reactivemeetup.domain.cooking.Ingredient;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise08 {

    public static void main(String[] args) {
        Observable<Ingredient> ingredient$ = ExampleStreams.ingredient$();

        // ASSIGNMENT: make a cake using the only fruits from the ingredient$ stream.
        //
        // HINT: Start by selecting the ingredients that you are going to use for the cake.
        //
        // HINT: Use the following expression to check if an ingredient is fruit:
        //
        //   EIngredientType.FRUIT.equals(ingredient.getType())
        //
        // HINT: When making the cake start with an "empty" cake: new Cake("Fruitcake")
        //
        // HINT: You can add an ingredient to a cake using the addIngredient method.
        
        Observable<Cake> cake$ = ingredient$
            .filter((ingredient) -> EIngredientType.FRUIT.equals(ingredient.getType()))
            .scan(new Cake("Fruitcake"), Cake::addIngredient);
        
        // If you've followed the recipe then you should end up with a delicious and healthy strawberry pineapple cake.
        
        cake$.subscribe(System.out::println);

        waitForStreamToComplete(ingredient$);
    }
}
