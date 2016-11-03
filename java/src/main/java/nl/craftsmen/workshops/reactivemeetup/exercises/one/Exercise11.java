package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.domain.geometry.IShape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise11 {

	public static void main(String[] args) {
		Observable<IShape> shape$ = ExampleStreams.shape$();
		
		shape$
			.reduce((a, b) -> {
				if (a.calculateArea() > b.calculateArea())
                    return a;
                return b;
			})
            .map((i) -> i.getName() + ": " + i.calculateArea())
			.subscribe(System.out::println);

		waitForStreamToComplete(shape$);
	}
}
