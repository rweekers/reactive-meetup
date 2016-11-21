package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise06 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$().take(5);
		
		// ASSIGNMENT: Compute the sum of all numbers emitted by the number$ stream. The result should be stored a new stream
		// that also contains the intermediate sums, for example given a stream of numbers 1, 2, 3, then the resulting stream
		// should emit the numbers 3 (1 + 2) and 6 (3 + 3).
		
		Observable<Integer> sum$ = number$
			.scan((sum, item) -> sum + item);
		
		// When implemented correctly you should see the following numbers: 1, 10, 14, 21, 27
		
		sum$.subscribe(System.out::println);

		waitForStreamToComplete(number$);
	}
}
