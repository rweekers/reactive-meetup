package nl.craftsmen.workshops.reactivemeetup.exercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise05 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.number$();
		number$
				.map((i) -> i * i)
				.subscribe(System.out::println);

		Thread.sleep(6000);
	}
}
