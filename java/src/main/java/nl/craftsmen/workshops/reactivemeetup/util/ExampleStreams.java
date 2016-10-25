package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.EIngredientType;
import nl.craftsmen.workshops.reactivemeetup.domain.Ingredient;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class ExampleStreams {

    public static Observable<Integer> number$() {
        return sample(Observable.from(new Integer[]{1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8}), 500);
    }

    public static Observable<Integer> numbersWithError$() {
        return number$().take(4).concatWith(Observable.error(new RuntimeException("uh oh! an error!")));
    }

    private static <T> Observable<T> sample(Observable<T> stream, int interval) {
        return Observable.interval(interval, TimeUnit.MILLISECONDS).zipWith(stream, (Long a, T b) -> b);
    }

    public static Observable<Ingredient> ingredient$() {
        return sample(Observable.from(new Ingredient[]{new Ingredient("Meel", EIngredientType.BASE),
                new Ingredient("Suiker", EIngredientType.SWEET), new Ingredient("Aardbei", EIngredientType.FRUIT),
                new Ingredient("Zout", EIngredientType.BASE), new Ingredient("Annanas", EIngredientType.FRUIT)}), 500);
    }

}
