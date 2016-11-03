package nl.craftsmen.workshops.reactivemeetup.exercises;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise05 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		number$
			.map((i) -> i * i)
			.subscribe(System.out::println);

		waitForStreamToComplete(number$);
	}
}
