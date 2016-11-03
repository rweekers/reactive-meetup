package nl.craftsmen.workshops.reactivemeetup.exercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

public class Exercise12 {

	public static void main(String[] args) {
		Observable<Integer> point$ = ExampleStreams.point$();
		point$
                .scan((x, y) -> x + y)
                .zipWith(point$, (total, points) -> "Points: " + points + " - total: " + total)
				.subscribe(System.out::println);

        waitForStreamToComplete(point$);
	}
}
