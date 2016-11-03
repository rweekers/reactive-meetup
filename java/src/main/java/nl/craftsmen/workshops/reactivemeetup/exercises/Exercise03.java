package nl.craftsmen.workshops.reactivemeetup.exercises;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise03 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		number$
			.filter((i) -> i % 2 == 0)
			.subscribe(System.out::println);
		
		waitForStreamToComplete(number$);
	}
}
