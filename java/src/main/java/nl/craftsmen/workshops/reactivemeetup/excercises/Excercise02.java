package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise02 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.numbersWithError$();
		
		number$.subscribe(
			(value) -> System.out.println("next: " + value),
			(error) -> System.out.println("error: " + error),
			()      -> System.out.println("completed")
		);
		
		Thread.sleep(6000);
	}

}
