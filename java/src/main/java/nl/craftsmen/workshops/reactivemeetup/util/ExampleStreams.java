package nl.craftsmen.workshops.reactivemeetup.util;

import nl.craftsmen.workshops.reactivemeetup.domain.cooking.EIngredientType;
import nl.craftsmen.workshops.reactivemeetup.domain.cooking.Ingredient;
import nl.craftsmen.workshops.reactivemeetup.domain.geometry.*;
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
        return sample(Observable.from(new Ingredient[]{new Ingredient("Flour", EIngredientType.BASE),
                new Ingredient("Suger", EIngredientType.SWEET), new Ingredient("Strawberry", EIngredientType.FRUIT),
                new Ingredient("Salt", EIngredientType.BASE), new Ingredient("Pineapple", EIngredientType.FRUIT)}), 500);
    }

    public static Observable<String> word$() {
        return sample(Observable.from(new String[]{"Jirble:", "spill", "a", "liquid", "by", "shaking", "or", "unsteady",
                "moving", "of", "the", "vessel"}), 500);
    }

    public static Observable<IShape> shape$() {
        return sample(Observable.from(new IShape[]{new Circle("Circle 30", 30), new Circle("Circle 40", 40), new Square("Square 20", 20),
                new Rectangle("Rectangle 50x20", 50, 20), new Triangle("Triangle 40x10", 40, 10), new Rectangle("Rectangle 45x40", 45, 40),
                new Square("Square 30", 30), new Triangle("Triangle 40x45", 40, 45), new Rectangle("Rectangle 10x70", 10, 70)}), 500);
    }

    public static Observable<Integer> point$() {
        return sample(Observable.from(new Integer[] {0, 3, 0, 3, 1, 3, 0, 0, 3, 0}), 500);
    }

}
