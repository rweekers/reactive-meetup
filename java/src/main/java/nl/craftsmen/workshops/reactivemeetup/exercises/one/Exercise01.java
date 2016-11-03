package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise01 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		number$.subscribe(System.out::println);
		
		waitForStreamToComplete(number$);
	}

}
