package nl.craftsmen.workshops.reactivemeetup.exercises;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.IShape;
import nl.craftsmen.workshops.reactivemeetup.domain.Subtotal;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import nl.craftsmen.workshops.reactivemeetup.util.Utils;
import rx.Observable;

public class Exercise10 {

    public static void main(String[] args) {
        Observable<IShape> shape$ = ExampleStreams.shape$();

        shape$
            .scan(new Subtotal(0, 0), (acc, shape) -> acc.add(shape.calculateArea()))
            .skip(1)
            .map((subtotal) -> subtotal.getSum() / subtotal.getCount())
            .map(Utils.NUMBER_FORMAT::format)
            .subscribe(System.out::println);

        waitForStreamToComplete(shape$);
    }
}
