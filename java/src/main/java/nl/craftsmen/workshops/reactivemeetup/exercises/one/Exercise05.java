package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise05 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		// ASSIGNMENT: create a new stream that emits the square of each number emitted by the number$ stream.
		
		Observable<Integer> squaredNumber$ = number$
			.map((i) -> i * i);
		
		// If implemented correctly, the application will output the following numbers: 1, 81, 16, 49, 36, 4, 4, 49, 9, 16, 64
		
		squaredNumber$.subscribe(System.out::println);

		waitForStreamToComplete(squaredNumber$);
	}
}
