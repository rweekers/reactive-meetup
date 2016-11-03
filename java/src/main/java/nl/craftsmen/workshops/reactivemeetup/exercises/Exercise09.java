package nl.craftsmen.workshops.reactivemeetup.exercises;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.IShape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import nl.craftsmen.workshops.reactivemeetup.util.Utils;
import rx.Observable;

public class Exercise09 {

	public static void main(String[] args) {
		Observable<IShape> shape$ = ExampleStreams.shape$();
		
		shape$
			.filter((i) -> i.calculateArea() > 500)
            .map(IShape::calculateCircumference)
            .map(Utils.NUMBER_FORMAT::format)
			.subscribe(System.out::println);

		waitForStreamToComplete(shape$);
	}
}
