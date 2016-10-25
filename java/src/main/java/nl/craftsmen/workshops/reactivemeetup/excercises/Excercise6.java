package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.domain.Cake;
import nl.craftsmen.workshops.reactivemeetup.domain.EIngredientType;
import nl.craftsmen.workshops.reactivemeetup.domain.Ingredient;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise6 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Ingredient> ingredient$ = ExampleStreams.ingredient$();

        Cake cake = new Cake("Fruitcake");

        ingredient$
                .filter((i) -> i.getType() == EIngredientType.FRUIT)
                .map((i) ->  {
                    cake.addIngredient(i);
                    return cake;
                })
                .subscribe(System.out::println);

        Thread.sleep(5000);
    }
}
