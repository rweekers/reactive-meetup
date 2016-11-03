package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.domain.Cake;
import nl.craftsmen.workshops.reactivemeetup.domain.EIngredientType;
import nl.craftsmen.workshops.reactivemeetup.domain.Ingredient;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise08 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Ingredient> ingredient$ = ExampleStreams.ingredient$();

        ingredient$
                .filter((i) -> i.getType() == EIngredientType.FRUIT)
                .scan(new Cake("Fruitcake"), Cake::addIngredient)
                .subscribe(System.out::println);

        Thread.sleep(6000);
    }
}
