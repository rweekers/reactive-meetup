package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.domain.Ingredient;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise6 {

    public static void main(String[] args) throws InterruptedException {
        Observable<Ingredient> ingredient$ = ExampleStreams.ingredient$();

        ingredient$
                .filter((i) -> i.getType().equals("Fruit"))
                .subscribe(System.out::println);

        Thread.sleep(5000);
    }
}
