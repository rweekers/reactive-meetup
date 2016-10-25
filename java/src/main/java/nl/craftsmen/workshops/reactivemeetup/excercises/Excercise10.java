package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.domain.IShape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;
import rx.observables.MathObservable;

public class Excercise10 {

	public static void main(String[] args) throws InterruptedException {
		Observable<IShape> shape$ = ExampleStreams.shape$();

        MathObservable.averageDouble(shape$.map(IShape::calculateArea))
            .subscribe(System.out::println);

		Thread.sleep(6000);
	}
}
