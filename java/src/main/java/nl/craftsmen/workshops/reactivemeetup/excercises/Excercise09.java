package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.domain.IShape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise09 {

	public static void main(String[] args) throws InterruptedException {
		Observable<IShape> shape$ = ExampleStreams.shape$();
		shape$
				.filter((i) -> i.calculateArea() > 500)
                .map(IShape::calculateCircumference)
				.subscribe(System.out::println);

		Thread.sleep(6000);
	}
}
