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

        ingredient$
            .filter((i) -> i.getType() == EIngredientType.FRUIT)
            .scan(new Cake("Fruitcake"), Cake::addIngredient)
            .subscribe(System.out::println);

        waitForStreamToComplete(ingredient$);
    }
}
