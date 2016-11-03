package nl.craftsmen.workshops.reactivemeetup.exercises;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise02 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.numbersWithError$();
		
		number$.subscribe(
			(value) -> System.out.println("next: " + value),
			(error) -> System.out.println("error: " + error),
			()      -> System.out.println("completed")
		);
		
		waitForStreamToComplete(number$);
	}

}
