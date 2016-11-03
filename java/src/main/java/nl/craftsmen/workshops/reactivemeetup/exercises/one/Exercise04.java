package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import nl.craftsmen.workshops.reactivemeetup.util.Utils;
import rx.Observable;

public class Exercise04 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		number$
			.filter((i) -> Utils.isPrime(i))
			.subscribe(System.out::println);

		waitForStreamToComplete(number$);
	}
}
