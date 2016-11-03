package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise12 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> point$ = ExampleStreams.point$();
		point$
				.subscribe(System.out::println);

		Thread.sleep(6000);
	}
}
