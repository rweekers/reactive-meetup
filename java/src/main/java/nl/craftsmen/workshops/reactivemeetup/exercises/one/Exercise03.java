package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise03 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		// Assignment: create a new stream based on the number$ stream that only emits even numbers.
		// If implemented correctly, the application will output the following numbers: 4, 6, 2, 2, 4, 8
		Observable<Integer> evenNumber$ = number$
			.filter((i) -> i % 2 == 0);
			
		evenNumber$.subscribe(System.out::println);
		
		waitForStreamToComplete(evenNumber$);
	}
}
